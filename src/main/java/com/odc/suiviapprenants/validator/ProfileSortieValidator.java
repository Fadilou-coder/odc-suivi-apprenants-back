package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.ProfilSortieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileSortieValidator {
    public static List<String> validate(ProfilSortieDto profilSortieDto) {
        List<String> errors = new ArrayList<>();

        if (profilSortieDto == null) {
            errors.add("Veuillez renseigner le libelle'");
            return errors;
        }
        if (!StringUtils.hasLength(profilSortieDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        return errors;
    }
}
