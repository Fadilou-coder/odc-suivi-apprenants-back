package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.GroupeTag;
import com.odc.suiviapprenants.model.Tag;
import com.odc.suiviapprenants.repository.GroupeTagRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import com.odc.suiviapprenants.service.TagService;
import com.odc.suiviapprenants.validator.TagValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private GroupeTagRepository groupeTagRepository;

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
    public TagDto put(Long id, TagDto tagDto)
    {
        if (id == null) {
            log.error("Tag ID is null");
        }
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Tag avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.TAG_NOT_FOUND));
        if(StringUtils.hasLength(tagDto.getLibelle())) {
            tag.setLibelle(tagDto.getLibelle());

        }
        tagRepository.flush();
        return TagDto.fromEntity(tag);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Tag ID is null");
        }

        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Tag avec l'ID = " + id + " n' est trouve dans la BDD",
                        ErrorCodes.TAG_NOT_FOUND));
        tag.setArchive(true);
        List<GroupeTag> groupeTag = groupeTagRepository.findAllByTagsId(id);
        if(!groupeTag.isEmpty()){
            groupeTag.forEach(groupeTag1 -> {
                groupeTag1.removeTag(tag);
            });
        }
        tagRepository.flush();
        groupeTagRepository.flush();
    }
}
