package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.NiveauEvaluationApi;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import com.odc.suiviapprenants.service.impl.NiveauEvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NiveauEvaluationController implements NiveauEvaluationApi {
    @Autowired
    private NiveauEvaluationServiceImpl niveauEvaluationService;

    @Override
    public NiveauEvaluationDto save(NiveauEvaluationDto niveauEvaluationDto) {
        return niveauEvaluationService.save(niveauEvaluationDto);
    }

    @Override
    public List<NiveauEvaluationDto> findAll() {
        return niveauEvaluationService.findAll();
    }

    @Override
    public NiveauEvaluationDto findById(Long id) {
        return niveauEvaluationService.findById(id);
    }

    @Override
    public void delete(Long id) {
    niveauEvaluationService.delete(id);
    }

    @Override
    public NiveauEvaluationDto put(NiveauEvaluationDto niveauEvaluationDto, Long id) {
        return niveauEvaluationService.put(niveauEvaluationDto, id);
    }
}
