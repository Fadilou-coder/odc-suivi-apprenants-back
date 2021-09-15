package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.ProfilSortieDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProfileSortieApi {

    @PostMapping("profileSortie/create")
    ProfilSortieDto save(@RequestBody ProfilSortieDto profilSortieDto);

    @GetMapping("/profileSortie")
    List<ProfilSortieDto> findAll();

    @GetMapping("/profileSortie/{id}")
    ProfilSortieDto findById(@PathVariable Long id);

    @DeleteMapping("/profileSortie/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/profileSortie/{id}")
    ProfilSortieDto put(@RequestBody ProfilSortieDto profilSortieDto ,@PathVariable Long id);
}
