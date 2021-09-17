package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> findAll();

    TagDto findById(Long id);

    TagDto put(Long id, TagDto tagDto);

    void delete(Long id);
}
