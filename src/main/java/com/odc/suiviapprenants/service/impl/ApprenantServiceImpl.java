package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.AppUser;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.UserRepository;
import com.odc.suiviapprenants.service.ApprenantService;
import com.odc.suiviapprenants.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ApprenantServiceImpl implements ApprenantService {
    ApprenantRepository apprenantRepository;
    UserRepository userRepository;

    @Override
    public ApprenantDto save(String username, String email, String prenom, String nom, String telephone, String adresse, String cni,
                             MultipartFile avatar, String dateNaissance,String motif, String genre, String niveauEntree, String handicap, String orphelin, String etablissementPrecedent) throws IOException {
        ApprenantDto apprenantDto = new ApprenantDto(
                null,
                username ,
                null,
                prenom,
                nom,
                email,
                cni,
                adresse,
                telephone,
                LocalDate.parse(dateNaissance),
                AdminServiceImpl.compressBytes(avatar.getBytes()),
                "APPRENANT",
                "EN COURS",
                "213456787DGFHJKJK",
                motif,
                genre,
                niveauEntree,
                handicap,
                orphelin,
                etablissementPrecedent
        );
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        apprenantDto.setPassword(encoder.encode("password"));
        validation(apprenantDto);
        return ApprenantDto.fromEntity(
                apprenantRepository.save(
                        ApprenantDto.toEntity(apprenantDto)
                )
        );
    }

    @Override
    public List<ApprenantDto> findAll() {
        return apprenantRepository.findAllByArchiveFalse().stream()
                .map(ApprenantDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ApprenantDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        return apprenantRepository.findByIdAndArchiveFalse(id).map(ApprenantDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.APPRENANT_NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("User ID is null");
        }

        Apprenant apprenant = apprenantRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun apprenant avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.APPRENANT_NOT_FOUND));
        apprenant.setArchive(true);
        apprenantRepository.flush();
    }

    @Override
    public ApprenantDto put(Long id, String username, String email, String prenom, String nom, String telephone, String adresse, String cni, MultipartFile avatar,
                            String dateNaissance, String etat, String motif, String genre, String niveauEntree, String handicap, String orphelin, String etablissementPrecedent) throws IOException {
        if (id == null) {
            log.error("User ID is null");
        }

        Apprenant apprenant = apprenantRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun apprenant avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.APPRENANT_NOT_FOUND));

        apprenant.setUsername(username);
        apprenant.setEmail(email);
        apprenant.setPrenom(nom);
        apprenant.setNom(nom);
        apprenant.setNumeroTelephone(telephone);
        apprenant.setAdresse(adresse);
        apprenant.setCni(cni);
        apprenant.setDateNaissance(LocalDate.parse(dateNaissance));
        apprenant.setAvatar(AdminServiceImpl.compressBytes(avatar.getBytes()));
        apprenant.setEtat(etat);
        apprenant.setMotif(motif);
        apprenant.setGenre(genre);
        apprenant.setNiveauEntree(niveauEntree);
        apprenant.setHandicap(handicap);
        apprenant.setOrphelin(orphelin);
        apprenant.setEtablissementPrecedent(etablissementPrecedent);

        ApprenantDto apprenantDto = ApprenantDto.fromEntity(apprenant);
        validation(apprenantDto);

        apprenantRepository.flush();
        return apprenantDto;
    }

    private void validation(ApprenantDto apprenantDto) {
        List<String> errors = UserValidator.Appvalidate(apprenantDto);
        if(userAlreadyExists(apprenantDto.getEmail(), apprenantDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.APPRENANT_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
        }
        if(userAlreadyExistsUsername(apprenantDto.getUsername(), apprenantDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme nom d'utilisateur existe deja", ErrorCodes.APPRENANT_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme nom d'utilisateur existe deja dans la BDD"));
        }

        if(userAlreadyExistsPhone(apprenantDto.getNumeroTelephone(), apprenantDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme numero de telephone existe deja", ErrorCodes.APPRENANT_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme numero de telephone existe deja dans la BDD"));
        }

        if(userAlreadyExistsCni(apprenantDto.getCni(), apprenantDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme cni existe deja", ErrorCodes.APPRENANT_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme cni existe deja dans la BDD"));
        }

        if (!errors.isEmpty()) {
            log.error("Admin is not valid {}", apprenantDto);
            throw new InvalidEntityException("L'admin n'est pas valide", ErrorCodes.APPRENANT_NOT_VALID, errors);
        }
    }
    private boolean userAlreadyExists(String email, Long id) {
        Optional<AppUser> user;
        if (id == null){
            user = userRepository.findByEmail(email);
        }else {
            user = userRepository.findByEmailAndIdNot(email, id);
        }
        return user.isPresent();
    }
    private boolean userAlreadyExistsUsername(String username, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByUsername(username);
        }else {
            user = userRepository.findByUsernameAndIdNot(username, id);
        }
        return user.isPresent();
    }
    private boolean userAlreadyExistsPhone(String phone, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByNumeroTelephone(phone);
        }else {
            user = userRepository.findByNumeroTelephoneAndIdNot(phone, id);
        }
        return user.isPresent();
    }

    private boolean userAlreadyExistsCni(String cni, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByCni(cni);
        }else {
            user = userRepository.findByCniAndIdNot(cni, id);
        }
        return user.isPresent();
    }

}
