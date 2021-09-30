package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.GroupeRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.PromoService;
import com.odc.suiviapprenants.validator.PromoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    GroupeRepository groupeRepository;
    @Override
    public PromoDto save(
            String langue,
            String referenceAgate,
            String title,
            String description,
            String lieu,
            String dateDebut,
            String dateFinProvisoir,
            String dateFinReelle,
            String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String> apprenantsEmail
    ) throws Exception{
        PromoDto promoDto = new PromoDto(
                langue,
                referenceAgate,
                title,
                description,
                lieu,
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFinProvisoir),
                LocalDate.parse(dateFinReelle),
                etat,
                AdminServiceImpl.compressBytes(avatarPromo.getBytes()),
                null
        );
        Groupe groupe1 = new Groupe();
        groupe1.setNomGroupe("Groupe_principale " + promoDto.getTitle());
        groupe1.setType("principale");
        groupe1.setStatut("ouvert");
        Referentiel referentiel1 = referentielRepository.findByLibelle(referentiel).get();
        promoDto.setReferentiel(ReferentielDto.fromEntity(referentiel1));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Apprenant> apprenants = new ArrayList<>();
        apprenantsEmail.forEach(email -> {
            if (apprenantRepository.findByEmail(email).isPresent()){
                if (apprenantRepository.findByEmail(email).get().getGroupes().isEmpty())
                {
                    throw new InvalidEntityException("Cet apprenant est deja dans un promo : " + email, ErrorCodes.APPRENANT_ALREADY_IN_USE);
                }
                apprenants.add(apprenantRepository.findByEmail(email).get());
            }
            else {
                Apprenant apprenant = new Apprenant();
                apprenant.setEmail(email);
                apprenant.setPassword(encoder.encode("passer"));
                apprenants.add(apprenant);
            }

        } );
        List<String> errors = PromoValidator.validatePromo(promoDto);
        if (!errors.isEmpty()) {
            log.error("promo is not valid {}", promoDto);
            throw new InvalidEntityException("promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID, errors);
        }
        groupe1.setPromo(PromoDto.toEntity(promoDto));
        groupe1.setApprenants(apprenants);
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

            promoDto.getGroupes().forEach(groupe -> {
                Promo promo = promoRepository.findById(id).get();
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
        else {
            List<String> errors = PromoValidator.validatePromo(promoDto);
            throw new InvalidEntityException("L'id promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID, errors);
        }
        return promoDto;
    }



}
