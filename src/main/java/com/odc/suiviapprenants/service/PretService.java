package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.PretDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PretService {

    PretDto save(
            String reference,
            String etat,
            MultipartFile convention,
            String libelle,
            String description,
            String apprenant) throws IOException;

    List<PretDto> findAll();

    PretDto findById(Long id);

    PretDto put(
            Long id,
            String reference,
            String etat) throws IOException;


}
