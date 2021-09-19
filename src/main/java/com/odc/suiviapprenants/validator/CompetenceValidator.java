package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class CompetenceValidator {
    public static List<String> validate(CompetenceDto competenceDto)
    {
        return getString(competenceDto == null, competenceDto.getLibelle());
    }

    public static List<String> validateAffectedNiveaux(CompetenceDto competenceDto){return null;}

    public static List<String> getString(boolean isCompetence, String libelle)
    {
        List<String> errors = new ArrayList<>();

        if(isCompetence)
        {
            errors.add("Veuillez renseigner le libellé");
            return errors;
        }
        if (!StringUtils.hasLength(libelle))
        {
            errors.add("Veuillez renseigner le libellé");
        }
        return errors;
    }
}
