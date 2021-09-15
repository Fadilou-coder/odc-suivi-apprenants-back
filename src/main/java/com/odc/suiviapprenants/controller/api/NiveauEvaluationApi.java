package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api("niveauEvaluation")
public interface NiveauEvaluationApi {

    @PostMapping("/niveauEvaluation/create")
    NiveauEvaluationDto save(@RequestBody NiveauEvaluationDto niveauEvaluationDto);

    @GetMapping("/niveauEvaluation")
    List<NiveauEvaluationDto> findAll();

    @GetMapping("/niveauEvaluation/{id}")
    NiveauEvaluationDto findById(@PathVariable Long id);

    @DeleteMapping("/niveauEvaluation/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/niveauEvaluation/{id}")
    NiveauEvaluationDto put(@RequestBody NiveauEvaluationDto niveauEvaluationDto ,@PathVariable Long id);
}
