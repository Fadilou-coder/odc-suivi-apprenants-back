package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GroupeCompetenceValidator {

    public static List<String> validate(GroupeCompetenceDto groupeCompetenceDto) {
        List<String> errors = new ArrayList<>();
        if(groupeCompetenceDto == null) {
            errors.add("Veuillez renseigner le libelle");
            errors.add("Veuillez renseigner la description");
            return errors;
        }
        if (!StringUtils.hasLength(groupeCompetenceDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        if (!StringUtils.hasLength(groupeCompetenceDto.getDescription())) {
            errors.add("Veuillez renseigner la description'");
        }
        return errors;
    }
}
