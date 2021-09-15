package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import org.springframework.stereotype.Service;
import java.util.List;


public interface NiveauEvaluationService {
    NiveauEvaluationDto save(NiveauEvaluationDto niveauEvaluationDto);

    List<NiveauEvaluationDto> findAll();

    NiveauEvaluationDto findById(Long id);

    void delete(Long id);

    NiveauEvaluationDto put(NiveauEvaluationDto niveauEvaluationDto,Long id);
}
