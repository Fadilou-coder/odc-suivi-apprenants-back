package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.exception.InvalidOperationException;
import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.PromoService;
import com.odc.suiviapprenants.validator.PromoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PromoServiceImpl implements PromoService {

    @Autowired
    PromoRepository promoRepository;
    @Autowired
    ReferentielRepository referentielRepository;
    @Autowired
    ApprenantRepository apprenantRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    GroupeRepository groupeRepository;

    public PromoDto insertInPromoDto (String langue, String title,
                                      String description, String lieu, String dateDebut,
                                      String dateFinProvisoir, String dateFinReelle, String etat,
                                      MultipartFile avatarPromo) throws IOException {
        PromoDto promoDto = new PromoDto();
        promoDto.setLangue(langue);
        promoDto.setTitle(title);
        promoDto.setDescription(description);
        promoDto.setLieu(lieu);
        promoDto.setDateDebut(LocalDate.parse(dateDebut));
        promoDto.setDateFinProvisoir(LocalDate.parse(dateFinProvisoir));
        promoDto.setDateFinReeelle(LocalDate.parse(dateFinReelle));
        promoDto.setEtat(etat);
        promoDto.setAvatarPromo(AdminServiceImpl.compressBytes(avatarPromo.getBytes()));

        return promoDto;

    }

    public PromoDto save(
            String langue, String title,
            String description, String lieu,
            String dateDebut, String dateFinProvisoir,
            String dateFinReelle, String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String> apprenantsEmail
    ) throws Exception{
        PromoDto promoDto = insertInPromoDto(
                langue, title,
                description, lieu,
                dateDebut, dateFinProvisoir,
                dateFinReelle, etat,  avatarPromo
        );
        Groupe groupe1 = new Groupe();
        groupe1.setNomGroupe("Groupe_principale " + promoDto.getTitle());
        groupe1.setType("principale");
        groupe1.setStatut("ouvert");
        Referentiel referentiel1 = referentielRepository.findByLibelle(referentiel).get();
        promoDto.setReferentiel(ReferentielDto.fromEntity(referentiel1));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Apprenant> apprenants = new ArrayList<>();
        List<GroupeDto> groupeDtoList = new ArrayList<>();

        if (apprenantsEmail !=null){
            for (String email : apprenantsEmail) {
                if (apprenantRepository.findByEmail(email).isPresent()) {
                    if (apprenantRepository.findByEmail(email).get().getGroupes().isEmpty()) {
                        throw new InvalidEntityException("Cet apprenant est deja dans un promo : " + email, ErrorCodes.APPRENANT_ALREADY_IN_USE);
                    }
                    apprenants.add(apprenantRepository.findByEmail(email).get());
                } else {
                    Apprenant apprenant = new Apprenant();
                    apprenant.setEmail(email);
                    apprenant.setPassword(encoder.encode("passer"));
                    apprenants.add(apprenant);
                }

            }
            groupe1.setApprenants(apprenants);
        }
        else {groupe1.setApprenants(null);}

        log.info(promoDto.toString());
       return getPromoDto(promoDto, groupe1, groupeDtoList);
    }

    private PromoDto getPromoDto(PromoDto promoDto, Groupe groupe1, List<GroupeDto> groupeDtoList) {
        groupe1.setPromo(PromoDto.toEntity(promoDto));
        groupeDtoList.add(GroupeDto.fromEntity(groupe1));
        List<String> errors = PromoValidator.validatePromo(promoDto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID, errors);
        }
        groupeRepository.save(groupe1);
        return promoDto;
    }

    @Override
    public List<PromoDto> findAll() {
        return promoRepository.findAllByArchiveFalse().stream()
                .map(PromoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PromoDto findById(Long id) {
        if (id == null) {
            log.error("Promo ID is null");
            return null;
        }

        return promoRepository.findById(id)
                .map(PromoDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun promo  avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCodes.PROMO_NOT_FOUND)
                );
    }

    @Override
    public PromoDto put(PromoDto promoDto, Long id) {
        if (promoRepository.findById(id).isPresent())
        {
            Promo promo = promoRepository.findById(id).get();
            if (promoDto.getGroupes() !=null){
                promoDto.getGroupes().forEach(groupe -> {
                    if (groupeRepository.findByIdAndPromo(groupe.getId(), promo).isPresent())
                    {
                        Groupe groupe2 = groupeRepository.findById(groupe.getId()).get();
                        groupe2.setStatut("cloturer");
                        groupe2.removeAllApprenant(groupe2.getApprenants());
                        groupeRepository.flush();
                    }
                    else {
                        Groupe groupe1 = new Groupe();
                        groupe1.setNomGroupe(groupe.getNomGroupe());
                        groupe1.setType(groupe.getType());
                        groupe1.setStatut(groupe.getStatut());
                        groupe1.setPromo(promo);
                        groupeRepository.save(groupe1);
                    }
                });
            }
            if (promoDto.getAdmins() !=null){
                promoDto.getAdmins().forEach(adminDto -> {
                    if (adminRepository.findById(adminDto.getId()).isPresent())
                    {
                        if (!promoRepository.findByEnCoursTrueAndArchiveFalseAndAdmins(adminRepository.findById(adminDto.getId()).get()).isPresent()){
                            promo.getAdmins().add(adminRepository.findById(adminDto.getId()).get());
                            promoRepository.flush();
                        }
                        else {
                            throw new InvalidOperationException(adminRepository.findById(adminDto.getId()).get().getUsername() + " Est deja dans un promo en cours");
                        }
                    }
                });
            }

        }
        else {
            List<String> errors = PromoValidator.validatePromo(promoDto);
            throw new InvalidEntityException("L'id promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID, errors);
        }
        return promoDto;
    }
}
