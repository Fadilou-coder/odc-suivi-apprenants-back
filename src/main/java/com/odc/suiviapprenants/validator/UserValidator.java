package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserValidator {

    public static List<String> validate(AdminDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto == null) {
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



        if (!StringUtils.hasLength(userDto.getUsername())) {
            errors.add("Veuillez renseigner le nom d'utilisateur'");
        }
        if (!StringUtils.hasLength(userDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom'");
        }
        if (!StringUtils.hasLength(userDto.getNom())) {
            errors.add("Veuillez renseigner le nom'");
        }
        if (!StringUtils.hasLength(userDto.getEmail())) {
            errors.add("Veuillez renseigner l'email'");
        }
        if (!StringUtils.hasLength(userDto.getCni())) {
            errors.add("Veuillez renseigner le cni'");
        }
        if (!StringUtils.hasLength(userDto.getAdresse())) {
            errors.add("Veuillez renseigner l'adresse'");
        }
        if (!StringUtils.hasLength(userDto.getNumeroTelephone())) {
            errors.add("Veuillez renseigner le numero de telephone'");
        }
        if (userDto.getDateNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance'");
        }
        if (userDto.getRole() == null) {
            errors.add("Veuillez renseigner le role");
        }



        return errors;
    }



}
