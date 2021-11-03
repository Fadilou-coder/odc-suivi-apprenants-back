package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.FormateurDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class FormateurValidator {

    public static List<String> validate(FormateurDto formateurDto) {
        return getStrings(formateurDto == null, formateurDto.getUsername(), formateurDto.getPrenom(),
                formateurDto.getNom(), formateurDto.getEmail(),
                formateurDto.getNumeroTelephone());
    }

    private static List<String> getStrings(boolean b, String username, String prenom,
                                           String nom, String email, String numeroTelephone) {
        List<String> errors = new ArrayList<>();

        if (b) {
            errors.add("Veuillez renseigner le username'");
            errors.add("Veuillez renseigner le prenom'");
            errors.add("Veuillez renseigner le nom'");
            errors.add("Veuillez renseigner l'email'");;
            errors.add("Veuillez renseigner le numero de telephone'");
            errors.add("Veuillez renseigner la date de naissance'");
            return errors;
        }


        testeValidator(username, prenom, nom, email, errors);

        if (!StringUtils.hasLength(numeroTelephone)) {
            errors.add("Veuillez renseigner le numero de telephone'");
        }
        if (!numeroTelephone.matches("^(33|7[05-8])[0-9]{7}$")){
            errors.add("Numero Telephone non valide");
        }

        return errors;
    }

    static void testeValidator(String username, String prenom, String nom, String email, List<String> errors) {
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
    }
}
