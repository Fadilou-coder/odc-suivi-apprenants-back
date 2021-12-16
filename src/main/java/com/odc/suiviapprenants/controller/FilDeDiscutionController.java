package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.FilDeDiscutionApi;
import com.odc.suiviapprenants.dto.FilDeDiscutionDto;
import com.odc.suiviapprenants.dto.MessageDto;
import com.odc.suiviapprenants.dto.ReponseDto;
import com.odc.suiviapprenants.service.FilDeDiscutionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FilDeDiscutionController implements FilDeDiscutionApi {

    FilDeDiscutionService filDeDiscutionService;

    @Override
    public List<FilDeDiscutionDto> findAll() {
        return filDeDiscutionService.findAll();
    }

    @Override
    public List<FilDeDiscutionDto> findAllByBrief(Long id, Long idApp) {
        return filDeDiscutionService.findAllByBrief(id, idApp);
    }


    @Override
    public MessageDto saveMessage(String libelle, MultipartFile pieceJointe, Long idApp, Long id) throws IOException {
        return filDeDiscutionService.saveMessage(libelle, pieceJointe, idApp, id);
    }

    @Override
    public ReponseDto repondre(String libelle, MultipartFile pieceJointe, Long id) throws IOException {
        return filDeDiscutionService.repondre(libelle, pieceJointe, id);
    }

    @Override
    public FilDeDiscutionDto saveFilDeDiscutionBrief(FilDeDiscutionDto filDeDiscutionDto, Long id, Long idApp) {
        return filDeDiscutionService.saveFilDeDiscutionBrief(filDeDiscutionDto, id, idApp);
    }

}
