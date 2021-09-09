package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();

        if (roleDto == null) {
            errors.add("Veuillez renseigner le libelle'");
            return errors;
        }
        if (!StringUtils.hasLength(roleDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        return errors;
    }

}
