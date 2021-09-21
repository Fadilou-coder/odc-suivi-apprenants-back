package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.PretDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("pret")
public interface PretApi {

    @PostMapping("/prets")
    PretDto save(
            @RequestParam("reference") String reference ,
            @RequestParam("etat") String etat,
            @RequestParam("convention") MultipartFile convention,
            @RequestParam("libelle") String libelle,
            @RequestParam("description") String description,
            @RequestParam("apprenant") String apprenant) throws IOException;

    @GetMapping("/prets")
    List<PretDto> findAll();

    @GetMapping("/prets/{id}")
    PretDto findById(@PathVariable  Long id);

    @PutMapping("/prets/{id}")
    PretDto put(
                @PathVariable Long id,
                @RequestParam("reference") String reference ,
                @RequestParam("etat") String etat) throws IOException;
}
