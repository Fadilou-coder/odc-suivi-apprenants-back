package com.odc.suiviapprenants.controller;

import com.odc.suiviapprenants.controller.api.TagApi;
import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TagController implements TagApi {
    private TagService tagService;

    @Override
    public List<TagDto> findAll() {
         return tagService.findAll();
    }

    @Override
    public TagDto findById(Long id) {
        return tagService.findById(id);
    }

    @Override
    public void delete(Long id) {
        tagService.delete(id);
    }

    @Override
    public TagDto put(Long id, TagDto tagDto) {
        return tagService.put(id, tagDto);
    }
}
