package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.PretApi;
import com.odc.suiviapprenants.dto.PretDto;
import com.odc.suiviapprenants.service.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PretController implements PretApi {

    @Autowired
    private PretService pretService;

    @Override
    public PretDto save(String reference, String etat, MultipartFile convention, String libelle, String description, String apprenant) throws IOException {
        return pretService.save(reference, etat, convention, libelle, description, apprenant);
    }

    @Override
    public List<PretDto> findAll() {
        return pretService.findAll();
    }

    @Override
    public PretDto findById(Long id) {
        return pretService.findById(id);
    }

    @Override
    public PretDto put(Long id, String reference, String etat) throws IOException {
        return pretService.put(id, reference, etat);
    }
}
