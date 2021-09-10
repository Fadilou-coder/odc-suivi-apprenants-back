package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.AdminDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    AdminDto save(
            String username ,
            String email,
            String prenom,
            String nom,
            String telephone,
            String adresse,
            String role,
            String cni,
            MultipartFile avatar,
            String dateNaissance
    ) throws IOException;

    List<AdminDto> findAll();

    AdminDto findById(Long id);

    void delete(Long id);

    AdminDto put(Long id);
}
