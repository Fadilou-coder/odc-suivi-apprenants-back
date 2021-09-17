package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.ReferentielDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReferentielService {

    ReferentielDto save(
            String libelle,
            String description,
            String critereEvaluation,
            String critereAdmission,
            MultipartFile programme,
            String grpCompetences
    ) throws IOException;

    List<ReferentielDto> findAll();

    ReferentielDto findById(Long id);

    ReferentielDto put(
            Long id,
            String libelle,
            String description,
            String critereEvaluation,
            String critereAdmission,
            MultipartFile programme,
            String grpCompetences
    ) throws IOException;

    void delete(Long id);
}
