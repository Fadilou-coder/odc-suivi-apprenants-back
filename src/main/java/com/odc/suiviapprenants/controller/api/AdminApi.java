package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.AdminDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("admin")
public interface AdminApi {

    @PostMapping("/admin/create")
    AdminDto save(
                  @RequestParam("username") String username ,
                  @RequestParam("email") String email,
                  @RequestParam("prenom") String prenom,
                  @RequestParam("nom") String nom,
                  @RequestParam("numeroTelephone") String telephone,
                  @RequestParam("adresse") String adresse,
                  @RequestParam("role") String role,
                  @RequestParam("cni") String cni,
                  @RequestParam("avatar") MultipartFile avatar,
                  @RequestParam("dateNaissance") String dateNaissance) throws IOException;

    @GetMapping("/admins")
    List<AdminDto> findAll();

    @GetMapping("/admins/{id}")
    AdminDto findById(@PathVariable  Long id);

    @DeleteMapping("/admins/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/admins/{id}")
    AdminDto put(@PathVariable Long id,
                 @RequestParam("username") String username ,
                 @RequestParam("email") String email,
                 @RequestParam("prenom") String prenom,
                 @RequestParam("nom") String nom,
                 @RequestParam("numeroTelephone") String telephone,
                 @RequestParam("adresse") String adresse,
                 @RequestParam("role") String role,
                 @RequestParam("cni") String cni,
                 @RequestParam("avatar") MultipartFile avatar,
                 @RequestParam("dateNaissance") String dateNaissance) throws IOException;
}
