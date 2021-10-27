package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.GroupeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class GroupeValidator {

    public static List<String> ValidatorGroupe(GroupeDto groupeDto)
    {

        return getString(groupeDto ==null,
                groupeDto.getNomGroupe(),
                groupeDto.getType(),
                groupeDto.getStatut()
        )
    ;}
        public static List<String> getString(

        boolean isGroupe,
                String nomGroupe,
                String type,
                String statut

    ) {
            List<String> errors = new ArrayList<>();
            if(isGroupe)
            {
                errors.add("Veuillez renseigner le nom du groupe");
                errors.add("Veuillez renseigner le type");
                errors.add("Veuillez renseigner le statut");
                return errors;
            }
            if (!StringUtils.hasLength(nomGroupe)) {
                errors.add("Veuillez renseigner le nom du groupe");
            }
            if (!StringUtils.hasLength(type)) {
                errors.add("Veuillez renseigner le type");
            }
            if (!StringUtils.hasLength(statut)) {
                errors.add("Veuillez renseigner le statut");
            }
            return errors;
        }
}
