package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.GroupeTagDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Api("groupe_tags")
public interface GroupeTagApi {

    @PostMapping("/groupe_tags/create")
    GroupeTagDto save(@RequestBody GroupeTagDto groupeTagDto) throws IOException;

    @GetMapping("/groupe_tags")
    List<GroupeTagDto> findAll();

    @GetMapping("/groupe_tags/{id}")
    GroupeTagDto findById(@PathVariable Long id);

    @DeleteMapping("/groupe_tags/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/groupe_tags/{id}")
    GroupeTagDto put(@PathVariable Long id, @RequestBody GroupeTagDto groupeTagDto);
}
