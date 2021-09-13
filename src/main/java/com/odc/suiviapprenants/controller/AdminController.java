package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.AdminApi;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminController implements AdminApi {

    @Autowired
    private AdminService adminService;

    @Override
    public AdminDto save(
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
    ) throws IOException {
        return adminService.save(
                 username ,
                 email,
                 prenom,
                 nom,
                 telephone,
                 adresse,
                 role,
                 cni,
                 avatar,
                 dateNaissance
        );
    }

    @Override
    public List<AdminDto> findAll() {
        return adminService.findAll();
    }

    @Override
    public AdminDto findById(Long id) {
        return adminService.findById(id);
    }

    @Override
    public void delete(Long id) {
         adminService.delete(id);
    }

    @Override
    public AdminDto put(Long id, String username, String email, String prenom, String nom, String telephone, String adresse, String role, String cni, MultipartFile avatar, String dateNaissance) throws IOException {
        return adminService.put(
                id,
                username ,
                email,
                prenom,
                nom,
                telephone,
                adresse,
                role,
                cni,
                avatar,
                dateNaissance
        );
    }


}
