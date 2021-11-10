package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.FilDeDiscutionDto;
import com.odc.suiviapprenants.dto.MessageDto;
import com.odc.suiviapprenants.dto.ReponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilDeDiscutionService {

    List<FilDeDiscutionDto> findAll();

    List<FilDeDiscutionDto> findAllByBrief(Long id, Long idApp);

    MessageDto saveMessage(String libelle, MultipartFile pieceJointe, Long idApp, Long id) throws IOException;

    ReponseDto repondre(String libelle, MultipartFile pieceJointe, Long id) throws IOException;

    FilDeDiscutionDto saveFilDeDiscutionBrief(FilDeDiscutionDto filDeDiscutionDto, Long id, Long idApp);
}
