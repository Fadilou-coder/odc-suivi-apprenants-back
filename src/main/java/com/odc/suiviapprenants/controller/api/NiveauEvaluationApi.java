package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api("niveauEvaluation")
public interface NiveauEvaluationApi {

    @PostMapping("/niveau_evaluations/create")
    NiveauEvaluationDto save(@RequestBody NiveauEvaluationDto niveauEvaluationDto);

    @GetMapping("/niveau_evaluations")
    List<NiveauEvaluationDto> findAll();

    @GetMapping("/niveau_evaluations/{id}")
    NiveauEvaluationDto findById(@PathVariable Long id);

    @DeleteMapping("/niveau_evaluations/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/niveau_evaluations/{id}")
    NiveauEvaluationDto put(@RequestBody NiveauEvaluationDto niveauEvaluationDto ,@PathVariable Long id);
}
