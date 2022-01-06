package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.FilDeDiscutionDto;
import com.odc.suiviapprenants.dto.MessageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilDeDiscutionService {

    List<FilDeDiscutionDto> findAll();

    FilDeDiscutionDto findAllByBrief(Long id);

    MessageDto saveMessage(String libelle, MultipartFile pieceJointe, Long idUser, Long id) throws IOException;

    FilDeDiscutionDto saveFilDeDiscutionBrief(FilDeDiscutionDto filDeDiscutionDto, Long id, Long idApp);
}
