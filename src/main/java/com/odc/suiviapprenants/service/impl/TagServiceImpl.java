package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.repository.TagRepository;
import com.odc.suiviapprenants.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<TagDto> findAll() {
        return tagRepository.findAllByArchiveFalse().stream()
                .map(TagDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(Long id) {
        if (id == null) {
            log.error("Tag ID is null");
            return null;
        }

        return tagRepository.findByIdAndArchiveFalse(id).map(TagDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Tag avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.TAG_NOT_FOUND)
        );
    }

    @Override
    public TagDto put(Long id, TagDto tagDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
