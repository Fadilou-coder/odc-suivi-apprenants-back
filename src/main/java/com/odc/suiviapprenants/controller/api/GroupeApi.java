package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api("groupe")
public interface GroupeApi {
    @PostMapping("/groupes/create")
    GroupeDto save(@RequestBody GroupeDto groupeDto) throws IOException;

    @GetMapping("/groupes")
    List<GroupeDto> findAll();

    @GetMapping("/groupes/{id}")
    GroupeDto findById(@PathVariable Long id);

    @GetMapping("/groupe/{id}/apprenants")
    List<ApprenantDto> findByApprenantById(@PathVariable Long id);

    @DeleteMapping("/groupes/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/groupes/{id}")
    GroupeDto put(@RequestBody GroupeDto groupeDto, @PathVariable Long id);

    @GetMapping("/groupes/{id}/apprenantsNonAffecter")
    List<ApprenantDto> findApprenantNonAffecterByGroupe (@PathVariable Long id);

    @GetMapping("/formateurs/{id}/groupes")
    List<GroupeDto> findByFormateur(@PathVariable Long id);

    @GetMapping("/formateurs/{id}/allGroupes")
    List<GroupeDto> findGroupeByFormateur(@PathVariable Long id);

    @GetMapping("/briefs/{idBr}/groupes")
    List<GroupeDto> findByBrief(@PathVariable("idBr") Long idBr);
}
