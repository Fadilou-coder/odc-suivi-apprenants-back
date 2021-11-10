package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.FilDeDiscutionDto;
import com.odc.suiviapprenants.dto.MessageDto;
import com.odc.suiviapprenants.dto.ReponseDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("Brief")
public interface FilDeDiscutionApi {
    @GetMapping("/filsDiscutions")
    List<FilDeDiscutionDto> findAll();

    @GetMapping("/briefs/{id}/apprenants/{idApp}/filsDiscutions")
    List<FilDeDiscutionDto> findAllByBrief(@PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PostMapping("/briefs/{id}/apprenants/{idApp}/filsDiscutions")
    FilDeDiscutionDto saveFilDeDiscutionBrief(@RequestBody FilDeDiscutionDto filDeDiscutionDto, @PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PostMapping("/apprenants/{idApp}/filsDiscutions/{id}/message")
    MessageDto saveMessage(@RequestParam("libelle") String libelle, @RequestParam("pieceJointe") MultipartFile pieceJointe, @PathVariable("idApp") Long idApp, @PathVariable("id") Long id) throws IOException;

    @PostMapping("/messages/{id}/reponse")
    ReponseDto repondre(@RequestParam("libelle") String libelle, @RequestParam("pieceJointe") MultipartFile pieceJointe, @PathVariable Long id) throws IOException;

}
