package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.exception.InvalidOperationException;
import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.PromoService;
import com.odc.suiviapprenants.validator.PromoValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PromoServiceImpl implements PromoService {

    PromoRepository promoRepository;
    ReferentielRepository referentielRepository;
    ApprenantRepository apprenantRepository;
    AdminRepository adminRepository;
    GroupeRepository groupeRepository;
    FormateurRepository formateurRepository;
    ProfilSortieRepository profilSortieRepository;

    public PromoDto insertInPromoDto (String langue, String title,
                                      String description, String lieu, String dateDebut,
                                      String dateFinProvisoir, String etat,
                                      MultipartFile avatarPromo) throws IOException {
        PromoDto promoDto = new PromoDto();
        promoDto.setLangue(langue);
        promoDto.setTitle(title);
        promoDto.setDescription(description);
        promoDto.setLieu(lieu);
        promoDto.setDateDebut(LocalDate.parse(dateDebut));
        promoDto.setDateFinProvisoire(LocalDate.parse(dateFinProvisoir));
        promoDto.setEtat(etat);
        promoDto.setAvatarPromo(AdminServiceImpl.compressBytes(avatarPromo.getBytes()));

        return promoDto;

    }

    public PromoDto save(
            String langue, String title,
            String description, String lieu,
            String dateDebut, String dateFinProvisoire, String etat,
            MultipartFile avatarPromo,
            String referentiel,
            List<String> apprenantsEmail, List<String> formateurs
    ) throws Exception{
        PromoDto promoDto = insertInPromoDto(
                langue, title,
                description, lieu,
                dateDebut, dateFinProvisoire, etat,  avatarPromo
        );
        Groupe groupe1 = new Groupe();
        groupe1.setNomGroupe("GROUPE PRINCIPALE");
        groupe1.setType("principal");
        groupe1.setStatut("ouvert");
        Referentiel referentiel1 = referentielRepository.findByLibelle(referentiel).get();
        List<FormateurDto> formateurList= new ArrayList<>();
        log.info(promoDto.toString());
        promoDto.setReferentiel(ReferentielDto.fromEntity(referentiel1));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<Apprenant> apprenants = new ArrayList<>();
         List<GroupeDto> groupeDtoList = new ArrayList<>();

        if (apprenantsEmail !=null){
            for (String email : apprenantsEmail) {
                findAndAddApprenants(encoder, apprenants, email);

            }
            groupe1.setApprenants(apprenants);
           // groupe1.setFormateurs(formateurList);
        }
        else {groupe1.setApprenants(null);}

        return getPromoDto(promoDto, groupe1,groupeDtoList,formateurs);
    }

    private PromoDto getPromoDto(PromoDto promoDto, Groupe groupe1,List<GroupeDto> groupeDtoList,List<String> formateurs) {
        groupe1.setPromo(PromoDto.toEntity(promoDto));
        List<Formateur> formateurList = new ArrayList<>();
        if (formateurs !=null){
            formateurs.forEach(formateur -> {
                if (formateurRepository.findByUsernameAndArchiveFalse(formateur).isPresent())
                {
                    if (!promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateurRepository.findByUsernameAndArchiveFalse(formateur).get()).isPresent()){
                        formateurList.add(formateurRepository.findByUsernameAndArchiveFalse(formateur).get());
                        groupe1.getPromo().setFormateurs(formateurList);
                    }
                    else {
                        throw new InvalidOperationException(formateurRepository.findByUsernameAndArchiveFalse(formateur).get().getUsername() + " est deja dans une promo en cours");
                    }
                }
            });
        }else {groupe1.getPromo().getFormateurs().add(null);}
          groupeDtoList.add(GroupeDto.fromEntity(groupe1));
        List<String> errors = PromoValidator.validatePromo(promoDto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID, errors);
        }
        groupeRepository.save(groupe1);
       // return PromoDto.fromEntity(groupe1.getPromo());
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
                .orElseThrow(() -> new EntityNotFoundException("Aucune promo  avec l'ID = " + id + " n'a ete trouve dans la BDD", ErrorCodes.PROMO_NOT_FOUND)
                );
    }

    @Override
    public PromoDto put(PromoDto promoDto, Long id) {
        if (promoRepository.findByIdAndArchiveFalse(id).isPresent())
        {
            Promo promo = promoRepository.findById(id).get();
            Groupe groupe = groupeRepository.findByNomGroupeAndPromo("GROUPE PRINCIPALE",promo).get();
//            if (promoDto.getGroupes() !=null){
//                promoDto.getGroupes().forEach(groupe -> {
//                    if (groupeRepository.findByIdAndPromo(groupe.getId(), promo).isPresent())
//                    {
//                        Groupe groupe2 = groupeRepository.findById(groupe.getId()).get();
//                        groupe2.setStatut("cloturer");
//                        groupe2.removeAllApprenant(groupe2.getApprenants());
//                        groupeRepository.flush();
//                    }
//                    else {
//                        Groupe groupe1 = new Groupe();
//                        groupe1.setNomGroupe(groupe.getNomGroupe());
//                        groupe1.setType(groupe.getType());
//                        groupe1.setStatut(groupe.getStatut());
//                        groupe1.setPromo(promo);
//                        groupeRepository.save(groupe1);
//                    }
//                });
//            }
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            List<Apprenant> apprenants = new ArrayList<>();
            valiadatePromo(promoDto);
            if (promoDto.getApprenantsEmail() !=null){
                for (String email : promoDto.getApprenantsEmail()) {
                    findAndAddApprenants(encoder, apprenants, email);

                }
                groupe.setApprenants(apprenants);
            }
            else {groupe.setApprenants(null);}
         //   if (promoDto.getFormateurs() !=null){
                promoDto.getFormateurs().forEach(formateurDto -> {
                    promo.getFormateurs().add(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get());
                    promoRepository.flush();
//                    if (formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).isPresent())
//                    {
//                        if (!promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get()).isPresent()){
//                            promo.getFormateurs().add(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get());
//                            promoRepository.flush();
//                        }
//                        else {
//                            throw new InvalidOperationException(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get().getUsername() + " Est deja dans un promo en cours");
//                        }
//                    }
                });
            //}

        }
        else {
            throw new InvalidEntityException("L'id promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID);
        }
        return PromoDto.fromEntity(promoRepository.findByIdAndArchiveFalse(id).get());
    }
    public void valiadatePromo(PromoDto promoDto){
        if(!promoDto.getApprenantsEmail().isEmpty()){
            promoDto.getApprenantsEmail().forEach(email->{
                if (apprenantRepository.findByEmail(email).isPresent()) {

                    if (apprenantRepository.findByEmail(email).get().getGroupes().isEmpty()) {
                        throw new InvalidEntityException("Cet apprenant est deja dans une promo : " + email, ErrorCodes.APPRENANT_ALREADY_IN_USE);
                    }
                }

            });
        }
        if (!promoDto.getFormateurs().isEmpty()){
            promoDto.getFormateurs().forEach(formateurDto -> {
                if (formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).isPresent() && promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get()).isPresent()){
                    throw new InvalidEntityException(formateurRepository.findByIdAndArchiveFalse(formateurDto.getId()).get().getUsername() +" Est deja dans un promo en cours: " , ErrorCodes.FORMATEUR_ALREADY_IN_USE);
                }

            });
        }
    }
    private void findAndAddApprenants(PasswordEncoder encoder, List<Apprenant> apprenants, String email) {
        if (apprenantRepository.findByEmail(email).isPresent()) {
            if (apprenantRepository.findByEmail(email).get().getGroupes().isEmpty()) {
                throw new InvalidEntityException("Cet apprenant est deja dans une promo : " + email, ErrorCodes.APPRENANT_ALREADY_IN_USE);
            }
            apprenants.add(apprenantRepository.findByEmail(email).get());
        } else {
            Apprenant apprenant = new Apprenant();
            apprenant.setEmail(email);
            apprenant.setPassword(encoder.encode("passer"));
            apprenants.add(apprenant);
        }
    }

    @Override
    public List<ApprenantDto> findApprenantsByPromoId(Long id) {
        if (id == null) {
            return null;
        }
        Promo promo = promoRepository.findByIdAndArchiveFalse(id).get();
        Groupe groupe = groupeRepository.findByNomGroupeAndPromo("GROUPE PRINCIPALE",promo).get();
        return groupe.getApprenants().stream().map(ApprenantDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfilSortieDto> findProfilSortieByPromoId(Long id) {
        if (id == null) {
            log.error("Promo Id is null");
            return null;
        }
         return profilSortieRepository.findByPromo(promoRepository.findById(id).get()).stream().map(ProfilSortieDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public PromoDto delete(Long id) {
        if (id == null) {
            return null;
        }
        Promo promo = promoRepository.findByIdAndArchiveFalse(id).get();
        promo.setArchive(true);
        promo.getGroupes().forEach(groupe -> {
            groupe.setArchive(true);
            groupe.getApprenants().forEach(apprenant -> apprenant.setArchive(true));
        });
        promo.getFormateurs().forEach(formateur -> formateur.setArchive(true));
        promo.getReferentiel().setArchive(true);
        promo.getReferentiel().getGroupeCompetences().forEach(groupeCompetence -> groupeCompetence.setArchive(true));
        promoRepository.flush();
        return PromoDto.fromEntity(promo);
    }
}
