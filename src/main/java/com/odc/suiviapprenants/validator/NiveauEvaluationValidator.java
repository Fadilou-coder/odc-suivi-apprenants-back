package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class NiveauEvaluationValidator {

    public static List<String> validateNiveauEvalutaion(NiveauEvaluationDto niveauEvaluationDto)
    {
        assert niveauEvaluationDto != null;
        return getString(false, niveauEvaluationDto.getLibelle(),
                niveauEvaluationDto.getGroupeAction(), niveauEvaluationDto.getCritereEvaluation(),
                niveauEvaluationDto.getReferentiel() == null);
    }


    public static List<String> getString(boolean isNiveauEvaluation, String libelle, String groupeAction, String critereEvaluation , boolean isReferentiel)
    {
        List<String> errors = new ArrayList<>();

        if (isNiveauEvaluation)
        {
            errors.add("Veuillez renseigner le libelle");
            errors.add("Veuillez renseigner le groupe d'action");
            errors.add("Veuillez renseigner le crictere d'evaluation");
            return errors;
        }
        if (!StringUtils.hasLength(libelle))
        {
            errors.add("Veuillez renseigner le libelle");
        }
        if (!StringUtils.hasLength(groupeAction))
        {
            errors.add("Veuillez renseigner le groupe d'action");
        }
        if(!StringUtils.hasLength(critereEvaluation))
        {
            errors.add("Veuillez renseigner le crictere d'evaluation");
        }
        if (isReferentiel)
        {
            errors.add("Veuillez renseigner le referentiel");
            return errors;
        }
        return errors;
    }
}
