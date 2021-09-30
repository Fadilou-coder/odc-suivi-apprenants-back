package com.odc.suiviapprenants.controller.api;
import com.odc.suiviapprenants.dto.GroupeDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("groupe")
public interface GroupeApi {
    @PostMapping("/groupe/create")
    GroupeDto save(@RequestBody GroupeDto groupeDto);

    @GetMapping("/groupe")
    List<GroupeDto> findAll();

    @GetMapping("/groupe/{id}")
    GroupeDto findById(@PathVariable Long id);

    @DeleteMapping("/groupe/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/groupe/{id}")
    GroupeDto put(@RequestBody GroupeDto groupeDto ,@PathVariable Long id);
}
