package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.ReferentielApi;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ReferentielController implements ReferentielApi {

    @Autowired
    private ReferentielService referentielService;

    @Override
    public ReferentielDto save(String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme) throws IOException {
        return referentielService.save(libelle, description, critereEvaluation, critereAdmission, programme);
    }

    @Override
    public List<ReferentielDto> findAll() {
        return referentielService.findAll();
    }

    @Override
    public ReferentielDto findById(Long id) {
        return referentielService.findById(id);
    }

    @Override
    public ReferentielDto put(Long id, String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme) throws IOException {
        return referentielService.put(id, libelle, description, critereEvaluation, critereAdmission, programme);
    }

    @Override
    public void delete(Long id) {
        referentielService.delete(id);
    }
}
