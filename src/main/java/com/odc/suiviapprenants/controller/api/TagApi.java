package com.odc.suiviapprenants.controller.api;

import com.odc.suiviapprenants.dto.TagDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("tags")
public interface TagApi {


    @GetMapping("/tags")
    List<TagDto> findAll();

    @GetMapping("/tags/{id}")
    TagDto findById(@PathVariable Long id);

    @DeleteMapping("/tags/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/tags/{id}")
    TagDto put(@PathVariable Long id,@RequestBody TagDto tagDto);
}
