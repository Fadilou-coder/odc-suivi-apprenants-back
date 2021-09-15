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
    public static List<String> validateCompetence(CompetenceDto competenceDto)
    {
        return getString(competenceDto == null, competenceDto.getLibelle());
    }

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
            errors.add("Defaal libele si competence bii");
        }
//        if (niveauEvaluationDtos.isEmpty())
//        {
//            errors.add("Veuillez mettre au moins un niveau d'evaluation");
//        }
//
//        if (niveauEvaluationDtos.toArray().length > 3)
//        {
//            errors.add("Vous ne pouvez mettre que trois niveau dans un referentiel");
//        }
//        if (niveauEvaluationDtos.toArray().length <= 3 | niveauEvaluationDtos.toArray().length <= 3){
//            for (NiveauEvaluationDto key: niveauEvaluationDtos){
//
//                if (key.getLibelle() == null | key.getLibelle().isEmpty()){
//                    errors.add("Veuillez mettre un libelle sur le niveau");
//                }
//
//                if(key.getCritereEvaluation() == null | !StringUtils.hasLength(key.getCritereEvaluation()))
//                {
//                    errors.add(key.getLibelle() +" :n'a pas de  crictere d'evaluation");
//                }
//                if (key.getGroupeAction() == null | !StringUtils.hasLength(key.getGroupeAction()))
//                {
//                    errors.add(key.getLibelle() +" : n'a pas de groupeDaction");
//                }
//            }
//        }
        return errors;
    }
}
