package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.ApprenantDto;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserValidator {

    public static List<String> validate(AdminDto userDto) {
        return getStrings(userDto == null, userDto.getUsername(), userDto.getPrenom(),
                userDto.getNom(), userDto.getEmail(), userDto.getCni(), userDto.getAdresse(),
                userDto.getNumeroTelephone(), userDto.getDateNaissance(), userDto.getRole() == null);
    }

    public static List<String> Appvalidate(ApprenantDto apprenantDto) {
        return getStrings(apprenantDto == null, apprenantDto.getUsername(), apprenantDto.getPrenom(),
                apprenantDto.getNom(), apprenantDto.getEmail(), apprenantDto.getCni(), apprenantDto.getAdresse(),
                apprenantDto.getNumeroTelephone(), apprenantDto.getDateNaissance(), false);
    }

    private static List<String> getStrings(boolean b, String username, String prenom,
                                           String nom, String email, String cni, String adresse,
                                           String numeroTelephone, LocalDate dateNaissance, boolean b2) {
        List<String> errors = new ArrayList<>();

        if (b) {
            errors.add("Veuillez renseigner le username'");
            errors.add("Veuillez renseigner le prenom'");
            errors.add("Veuillez renseigner le nom'");
            errors.add("Veuillez renseigner l'email'");
            errors.add("Veuillez renseigner le cni'");
            errors.add("Veuillez renseigner l'adresse'");
            errors.add("Veuillez renseigner le numero de telephone'");
            errors.add("Veuillez renseigner la date de naissance'");
            errors.add("Veuillez renseigner le role'");
            return errors;
        }


        if (!StringUtils.hasLength(username)) {
            errors.add("Veuillez renseigner le nom d'utilisateur'");
        }
        if (!StringUtils.hasLength(prenom)) {
            errors.add("Veuillez renseigner le prenom'");
        }
        if (!StringUtils.hasLength(nom)) {
            errors.add("Veuillez renseigner le nom'");
        }
        if (!StringUtils.hasLength(email)) {
            errors.add("Veuillez renseigner l'email'");
        }
        if (!email.matches("^[a-z0-9.-]+@[a-z0-9.-]{2,}\\.[a-z]{2,4}$")){
            errors.add("email non valide");
        }
        if (!StringUtils.hasLength(cni)) {
            errors.add("Veuillez renseigner le cni'");
        }
        if (!cni.matches("[0-9] [0-9]{3} [0-9]{4} [0-9]{5}")){
            errors.add("CNI non valide");
        }
        if (!StringUtils.hasLength(adresse)) {
            errors.add("Veuillez renseigner l'adresse'");
        }
        if (!StringUtils.hasLength(numeroTelephone)) {
            errors.add("Veuillez renseigner le numero de telephone'");
        }
        if (!numeroTelephone.matches("^(33|7[05-8])[0-9]{7}$")){
            errors.add("Numero Telephone non valide");
        }
        if (dateNaissance == null) {
            errors.add("Veuillez renseigner la date de naissance'");
        }
        if (b2) {
            errors.add("Veuillez renseigner le role");
        }


        return errors;
    }





}
