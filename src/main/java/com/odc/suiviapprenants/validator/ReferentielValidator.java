package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.ReferentielDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReferentielValidator {

    public static List<String> validate(ReferentielDto refDto) {
        List<String> errors = new ArrayList<>();

        if (refDto == null) {
            errors.add("Veuillez renseigner le libelle'");
            errors.add("Veuillez renseigner la description'");
            errors.add("Veuillez renseigner le critere d'evaluation'");
            errors.add("Veuillez renseigner le critere d'admission'");
            errors.add("Veuillez renseigner le programme'");
            return errors;
        }
        if (!StringUtils.hasLength(refDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        if (!StringUtils.hasLength(refDto.getDescription())) {
            errors.add("Veuillez renseigner la description'");
        }
        if (!StringUtils.hasLength(refDto.getCritereEvaluation())) {
            errors.add("Veuillez renseigner le critere d'evaluation'");
        }
        if (!StringUtils.hasLength(refDto.getCritereAdmission())) {
            errors.add("Veuillez renseigner le le critere d'admission'");
        }
        if (refDto.getProgramme() == null) {
            errors.add("Veuillez renseigner le programme'");
        }
        return errors;
    }
}
