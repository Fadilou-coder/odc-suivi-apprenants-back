package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.FilDeDiscutionDto;
import com.odc.suiviapprenants.dto.MessageDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("Brief")
public interface FilDeDiscutionApi {
    @GetMapping("/filsDiscutions")
    List<FilDeDiscutionDto> findAll();

    @GetMapping("/briefs/{id}/filsDiscutions")
    FilDeDiscutionDto findAllByBrief(@PathVariable("id") Long id);

    @PostMapping("/briefs/{id}/apprenants/{idApp}/filsDiscutions")
    FilDeDiscutionDto saveFilDeDiscutionBrief(@RequestBody FilDeDiscutionDto filDeDiscutionDto, @PathVariable("id") Long id, @PathVariable("idApp") Long idApp);

    @PostMapping("/users/{idUser}/filsDiscutions/{id}/message")
    MessageDto saveMessage(@RequestParam("libelle") String libelle, @RequestParam("pieceJointe") MultipartFile pieceJointe, @PathVariable("idUser") Long idUser, @PathVariable("id") Long id) throws IOException;

}
