package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.GroupeTagDto;
import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.GroupeTagRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import com.odc.suiviapprenants.service.GroupeTagService;
import com.odc.suiviapprenants.validator.GroupeTagValidator;
import com.odc.suiviapprenants.validator.TagValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupeTagServiceImpl implements GroupeTagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private  GroupeTagRepository groupeTagRepository;

    @Override
    public GroupeTagDto save(GroupeTagDto groupeTagDto) {
        List<String> errors = GroupeTagValidator.validate(groupeTagDto);
        if (!errors.isEmpty()) {
            log.error("GroupeTag is not valid {}", groupeTagDto);
            throw new InvalidEntityException("Le groupe de tags n'est pas valide", ErrorCodes.GROUPE_TAG_NOT_VALID, errors);
        }
        if(groupeTagAlreadyExists(groupeTagDto.getLibelle())) {
            throw new InvalidEntityException("Un autre  groupe de tag avec le meme libelle existe deja", ErrorCodes.GROUPE_TAG_ALREADY_IN_USE,
                    Collections.singletonList("Un autre groupe de tag avec le meme libelle existe deja dans la BDD"));
        }

        List<TagDto> tagList = new ArrayList<>();

        handleTag(groupeTagDto, tagList);

        return (
                GroupeTagDto.fromEntity(
                        groupeTagRepository.save(
                                GroupeTagDto.toEntity(groupeTagDto)
                        )
                )
        );
    }

    @Override
    public GroupeTagDto put(Long id, GroupeTagDto groupeTagDto) {
        if (id == null) {
            log.error("Groupe de Tag ID is null");
        }
        List<TagDto> tagList = new ArrayList<>();

        GroupeTag groupeTag = groupeTagRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Groupe de Tag avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_TAG_NOT_FOUND));
        handleTag(groupeTagDto, tagList);
        groupeTag.setLibelle(groupeTagDto.getLibelle());
        groupeTag.setTags(groupeTagDto.getTags().stream().map(TagDto::toEntity).collect(Collectors.toList()));
        groupeTagRepository.flush();

        return GroupeTagDto.fromEntity(groupeTag);
    }

    private void handleTag(GroupeTagDto groupeTagDto, List<TagDto> tagList) {
        groupeTagDto.getTags().forEach(tag->{
            if (tagRepository.findByLibelle(tag.getLibelle()).isPresent()) {
                tagList.add(TagDto.fromEntity(tagRepository.findByLibelle(tag.getLibelle()).get()));
            }else {
                if (!TagValidator.validate(tag).isEmpty()){
                    throw new InvalidEntityException("Le libelle du tag ne doit pas être vide", ErrorCodes.TAG_NOT_VALID, TagValidator.validate(tag));
                }
                tagList.add(TagDto.fromEntity(tagRepository.save(TagDto.toEntity(tag))));
            }
        });
        groupeTagDto.setTags(tagList);
    }

    private boolean groupeTagAlreadyExists(String libelle) {
        Optional<GroupeTag> groupeTag = groupeTagRepository.findByLibelle(libelle);
        return groupeTag.isPresent();
    }

    @Override
    public Page<GroupeTag> findAll(Optional<Integer> page,
                                   Optional<String> sortBy) {
        return groupeTagRepository.findAllByArchiveFalse(PageRequest.of(
                page.orElse(0),
                3,
                Sort.Direction.ASC, sortBy.orElse("id")
        ));
    }

    @Override
    public GroupeTagDto findById(Long id) {
        if (id == null) {
            log.error("GroupeTag ID is null");
            return null;
        }

        return groupeTagRepository.findByIdAndArchiveFalse(id).map(GroupeTagDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Groupe de Tag avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_TAG_NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Groupe de Tag ID is null");
        }

        GroupeTag groupeTag = groupeTagRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Groupe de Tag avec l'ID = " + id + " n' est trouve dans la BDD",
                        ErrorCodes.GROUPE_TAG_NOT_FOUND));
        groupeTag.setArchive(true);
        List<Tag> tags = tagRepository.findAllByGroupeTagsId(id);
        if(!tags.isEmpty()){
            tags.forEach(tag -> {
                tag.removeGroupeTag(groupeTag);
            });
        }
        groupeTagRepository.flush();
        tagRepository.flush();
    }
}
