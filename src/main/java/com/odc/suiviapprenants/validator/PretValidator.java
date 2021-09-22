package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.PretDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class PretValidator {
    public static List<String> validate(PretDto pretDto)
    {
        List<String> errors = new ArrayList<>();

        if(pretDto == null)
        {
            errors.add("Veuillez renseigner le référence");
            errors.add("Veuillez renseigner le etat");
            errors.add("Veuillez renseigner le convention");
            errors.add("Veuillez renseigner le libelle du materiel");
            errors.add("Veuillez renseigner la description du materiel");
            errors.add("Veuillez renseigner l'apprenant");
            return errors;
        }
        if (!StringUtils.hasLength(pretDto.getReference()))
        {
            errors.add("Veuillez renseigner le référence");
        }
        if (!StringUtils.hasLength(pretDto.getEtat()))
        {
            errors.add("Veuillez renseigner l'etat");
        }
        if (!StringUtils.hasLength(pretDto.getMateriel().getLibelle()))
        {
            errors.add("Veuillez renseigner le libelle du matériel");
        }
        if (!StringUtils.hasLength(pretDto.getMateriel().getDescription()))
        {
            errors.add("Veuillez renseigner la description du matériel");
        }
        if (pretDto.getApprenant() == null)
        {
            errors.add("Veuillez renseigner l'apprenant");
        }
        if (pretDto.getConvention() == null)
        {
            errors.add("Veuillez renseigner la convention");
        }
        return errors;
    }
}
