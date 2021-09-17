package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.GroupeTagDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GroupeTagValidator {

    public static List<String> validate(GroupeTagDto groupeTagDto) {
        List<String> errors = new ArrayList<>();

        if (groupeTagDto == null) {
            errors.add("Veuillez renseigner le libelle'");
            return errors;
        }
        if (!StringUtils.hasLength(groupeTagDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        return errors;
    }
}
