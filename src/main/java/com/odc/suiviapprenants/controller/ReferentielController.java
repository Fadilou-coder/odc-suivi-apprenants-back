package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.ReferentielApi;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.service.ReferentielService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReferentielController implements ReferentielApi {
    private ReferentielService referentielService;

    @Override
    public ReferentielDto save(String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme, String grpCompetences) throws IOException {
        return referentielService.save(libelle, description, critereEvaluation, critereAdmission, programme, grpCompetences);
    }

    @Override
    public List<ReferentielDto> findAll() {
        return referentielService.findAll();
    }

    @Override
    public List<GroupeCompetenceDto> findGroupeCompetences(Long id) {
        return referentielService.findGroupeCompetences(id);
    }

    @Override
    public GroupeCompetenceDto findOneGroupeCompetence(Long id_referentiel, Long id_groupeCompetence) {
        return referentielService.findOneGroupeCompetence(id_referentiel, id_groupeCompetence);
    }

    @Override
    public ReferentielDto findById(Long id) {
        return referentielService.findById(id);
    }

    @Override
    public ReferentielDto put(Long id, String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme, String grpCompetences) throws IOException {
        return referentielService.put(id, libelle, description, critereEvaluation, critereAdmission, programme, grpCompetences);
    }

    @Override
    public void delete(Long id) {
        referentielService.delete(id);
    }
}
