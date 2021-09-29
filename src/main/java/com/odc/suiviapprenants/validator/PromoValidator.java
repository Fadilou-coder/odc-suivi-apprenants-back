package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.PromoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class PromoValidator {

    public static List<String> validatePromo(PromoDto promoDto)
    {
       return getString(promoDto ==null,
                promoDto.getLangue(),
                promoDto.getTitle(),
                promoDto.getDescription(),
                promoDto.getLieu(),
                promoDto.getEtat(),
                promoDto.getDateDebut(),
                promoDto.getDateFinProvisoir(),
                promoDto.getDateFinReeelle(),
                promoDto.getReferentiel() == null,
                promoDto.getGroupes() == null);
    }

    public static List<String> getString(
            boolean isPromo,
            String langue , String title ,
            String description, String lieu,
            String etat, LocalDate dateDebut,
            LocalDate dateFinProvisoir, LocalDate dateFinReelle,
            boolean isReferentiel, boolean isGroupePrincipale
    )
    {
        List<String> errors = new ArrayList<>();

        if(isPromo)
        {
            errors.add("Veuillez renseigner la langue");
            errors.add("Veuillez renseigner le titre");
            errors.add("Veuillez renseigner la description");
            errors.add("Veuillez renseigner le lieu");
            errors.add("Veuillez renseigner l'etat");
            errors.add("Veuillez renseigner la date de debut");
            errors.add("Veuillez renseigner la date de fin provisoir");
            errors.add("Veuillez renseigner la date de fin reelle");
            return errors;
        }

        if (isReferentiel)
        {
            errors.add("Veuillez renseigner le referentiel");
        }
        if (isGroupePrincipale)
        {
            errors.add("Veuillez renseigner le groupe");
        }
        if (!StringUtils.hasLength(langue))
        {
            errors.add("Veuillez renseigner la langue");
        }
        if (!StringUtils.hasLength(title))
        {
            errors.add("Veuillez renseigner le titre");
        }
        if (!StringUtils.hasLength(description))
        {
            errors.add("Veuillez renseigner la description");
        }
        if (!StringUtils.hasLength(lieu))
        {
            errors.add("Veuillez renseigner le lieu");
        }

        if (!StringUtils.hasLength(etat))
        {
            errors.add("Veuillez renseigner l'etat");
        }
        if (dateDebut == null)
        {
            errors.add("Veuillez renseigner la date de debut");
        }
        if (dateFinProvisoir == null)
        {
            errors.add("Veuillez renseigner la date de fin provisoir");
        }
        if (dateFinReelle == null)
        {
            errors.add("Veuillez renseigner la date de fin reelle");
        }
        return errors;
    }
}
