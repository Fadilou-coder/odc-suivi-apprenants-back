package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import com.odc.suiviapprenants.service.GroupeCompetenceService;
import com.odc.suiviapprenants.validator.GroupeCompetenceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Service
@Slf4j
public class GroupeCompetenceServiceImpl implements GroupeCompetenceService {
    @Autowired
    GroupeCompetenceRepository repository;
    @Autowired
    CompetenceRepository competenceRepository;
    @Autowired
    TagRepository tagRepository;

    @Override
    public List<GroupeCompetenceDto> findAll() {
        return repository.findAllByArchiveFalse().stream()
                .map(GroupeCompetenceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GroupeCompetenceDto save(GroupeCompetenceDto groupeCompetenceDto) {
        List<String> errors = GroupeCompetenceValidator.validate(groupeCompetenceDto);
        if(groupeCompetenceAlreadyExist(groupeCompetenceDto.getLibelle())) {
            throw new InvalidEntityException("Un autre groupe de compétence avec le même libelle existe deja", ErrorCodes.GROUPE_COMPETENCE_ALREADY_IN_USE,
                    Collections.singletonList("Un autre groupe de compétence avec le même libelle existe deja dans la BDD"));
        }

        if (!errors.isEmpty()) {
            log.error("Groupe de compétence is not valid {}", groupeCompetenceDto);
            throw new InvalidEntityException("Le groupe de compétence n'est pas valide", ErrorCodes.GROUPE_COMPETENCE_NOT_VALID, errors);
        }

        GroupeCompetence groupeCompetence = GroupeCompetenceDto.toEntity(groupeCompetenceDto);
        groupeCompetenceDto.getCompetences().forEach(competenceDto -> {
            if(competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle()).isPresent()) {
                groupeCompetence.addCompetence(competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle()).get());
                groupeCompetence.getCompetences().remove(CompetenceDto.toEntity(competenceDto));
            }
            else {
                //put code for competence validator
            }
        });
        groupeCompetenceDto.getTags().forEach(tagDto -> {
            if(tagRepository.findByLibelleAndArchiveFalse(tagDto.getLibelle()).isPresent()) {
                groupeCompetence.addTag(tagRepository.findByLibelleAndArchiveFalse(tagDto.getLibelle()).get());
                groupeCompetence.getTags().remove(TagDto.toEntity(tagDto));
            }
            else {
                //put code for empty libelle tag validator
            }
        });
        return GroupeCompetenceDto.fromEntity(
                repository.save(groupeCompetence)
        );
    }

    @Override
    public GroupeCompetenceDto findById(Long id) {
        if(id == null) {
            log.error("Groupe competence ID is null");
            return null;
        }
        return repository.findByIdAndArchiveFalse(id).map(GroupeCompetenceDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun groupe de compétence avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND)
                );
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            log.error("Groupe competence ID is null");
        }
        GroupeCompetence groupeCompetence = repository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun groupe de compétence avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND));
        groupeCompetence.setArchive(false);
    }

    @Override
    public GroupeCompetenceDto edit(GroupeCompetenceDto groupeCompetenceDto) {
        return null;
    }

    private boolean groupeCompetenceAlreadyExist(String libelle) {
        return repository.findByLibelleAndArchiveFalse(libelle).isPresent();
    }

    /*private void handleTag(GroupeCompetenceDto groupeCompetenceDto, List<TagDto> tagList) {
        groupeCompetenceDto.getTags().forEach(tag->{
            if (tagRepository.findByLibelleAndArchiveFalse(tag.getLibelle()).isPresent()) {
                tagList.add(TagDto.fromEntity(tagRepository.findByLibelleAndArchiveFalse(tag.getLibelle()).get()));
            }else {
                if (!TagValidator.validate(tag).isEmpty()){
                    throw new InvalidEntityException("Le libelle du tag ne doit pas être vide", ErrorCodes.TAG_NOT_VALID, TagValidator.validate(tag));
                }
                tagList.add(TagDto.fromEntity(tagRepository.save(TagDto.toEntity(tag))));
            }
        });
    }*/
}
