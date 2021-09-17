package com.odc.suiviapprenants.validator;

import com.odc.suiviapprenants.dto.TagDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TagValidator {

    public static List<String> validate(TagDto tagDto) {
        List<String> errors = new ArrayList<>();

        if (tagDto == null) {
            errors.add("Veuillez renseigner le libelle'");
            return errors;
        }
        if (!StringUtils.hasLength(tagDto.getLibelle())) {
            errors.add("Veuillez renseigner le libelle'");
        }
        return errors;
    }
}
