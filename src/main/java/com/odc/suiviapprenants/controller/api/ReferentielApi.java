package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.ReferentielDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("referentiel")
public interface ReferentielApi {

    @PostMapping("/admin/referentiels")
    ReferentielDto save(
            @RequestParam String libelle,
            @RequestParam String description,
            @RequestParam String critereEvaluation,
            @RequestParam String critereAdmission,
            @RequestParam MultipartFile programme
    ) throws IOException;

    @GetMapping("/admin/referentiels")
    List<ReferentielDto> findAll();

    @GetMapping("/admin/referentiels/{id}")
    ReferentielDto findById(@PathVariable Long id);

    @PutMapping("/admin/referentiels/{id}")
    ReferentielDto put(
            @PathVariable Long id,
            @RequestParam String libelle,
            @RequestParam String description,
            @RequestParam String critereEvaluation,
            @RequestParam String critereAdmission,
            @RequestParam MultipartFile programme
    ) throws IOException;

    @DeleteMapping("/admin/referentiels/{id}")
    void delete(@PathVariable Long id);
}
