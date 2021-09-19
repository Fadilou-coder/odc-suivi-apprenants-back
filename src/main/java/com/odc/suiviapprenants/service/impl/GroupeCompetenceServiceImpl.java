package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.dto.TagDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.model.Tag;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import com.odc.suiviapprenants.service.GroupeCompetenceService;
import com.odc.suiviapprenants.validator.CompetenceValidator;
import com.odc.suiviapprenants.validator.GroupeCompetenceValidator;
import com.odc.suiviapprenants.validator.TagValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class GroupeCompetenceServiceImpl implements GroupeCompetenceService {
    GroupeCompetenceRepository groupeCompetenceRepository;
    CompetenceRepository competenceRepository;
    TagRepository tagRepository;

    @Override
    public List<GroupeCompetenceDto> findAll() {
        return groupeCompetenceRepository.findAllByArchiveFalse().stream()
                .map(GroupeCompetenceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompetenceDto> findCompetences(Long id) {
        if(id == null) {
            log.error("Groupe competence ID is null");
            return null;
        }
        if(!groupeCompetenceRepository.findByIdAndArchiveFalse(id).isPresent()) {
            throw new EntityNotFoundException(
                    "Aucun groupe de compétence avec l'ID = " + id + " ne se trouve dans la BDD",
                    ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND
                );
        }
        return competenceRepository.findAllByGroupeCompetencesId(id).stream()
                .map(CompetenceDto::fromEntity)
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
        addCompetences(groupeCompetenceDto, groupeCompetence);
        addTags(groupeCompetenceDto, groupeCompetence);

        return GroupeCompetenceDto.fromEntity(
                groupeCompetenceRepository.save(groupeCompetence)
        );
    }

    @Override
    public GroupeCompetenceDto findById(Long id) {
        if(id == null) {
            log.error("Groupe competence ID is null");
            return null;
        }
        return groupeCompetenceRepository.findByIdAndArchiveFalse(id).map(GroupeCompetenceDto::fromEntity).orElseThrow(() ->
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
        GroupeCompetence groupeCompetence = groupeCompetenceRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun groupe de compétence avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND));
        groupeCompetence.setArchive(true);

        List<Competence> competences = competenceRepository.findAllByGroupeCompetencesId(id);
        if(!competences.isEmpty()) {
            competences.forEach(groupeCompetence::removeCompetence);
        }

        List<Tag> tags = tagRepository.findAllByGroupeCompetencesId(id);
        if(!tags.isEmpty()){
            tags.forEach(groupeCompetence::removeTag);
        }

        groupeCompetenceRepository.flush();
    }

    @Override
    public GroupeCompetenceDto edit(Long id, GroupeCompetenceDto groupeCompetenceDto) {
        if(id == null) {
            log.error("Groupe competence ID is null");
        }
        GroupeCompetence groupeCompetence = groupeCompetenceRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun groupe de compétence avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND));
        groupeCompetence.setLibelle(groupeCompetenceDto.getLibelle());
        groupeCompetence.setDescription(groupeCompetenceDto.getDescription());


        groupeCompetenceDto.getCompetences().forEach(competenceDto -> {
            if(competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle()).isPresent()) {
            }
        });

        groupeCompetenceRepository.flush();
        return GroupeCompetenceDto.fromEntity(groupeCompetence);
    }

    private boolean groupeCompetenceAlreadyExist(String libelle) {
        return groupeCompetenceRepository.findByLibelleAndArchiveFalse(libelle).isPresent();
    }

    private void addCompetences(GroupeCompetenceDto groupeCompetenceDto, GroupeCompetence groupeCompetence) {
        if(!groupeCompetenceDto.getCompetences().isEmpty()) {
            groupeCompetenceDto.getCompetences().forEach(competenceDto -> {
                if (!CompetenceValidator.validate(competenceDto).isEmpty()){
                    throw new InvalidEntityException("Le libelle de la compétence ne doit pas être vide", ErrorCodes.COMPETENCE_NOT_VALID, CompetenceValidator.validate(competenceDto));
                }
                else {
                    if(competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle()).isPresent()) {
                        groupeCompetence.addCompetence(competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle()).get());
                        groupeCompetence.getCompetences().remove(CompetenceDto.toEntity(competenceDto));
                    }
                }
            });
        }
    }

    private void addTags(GroupeCompetenceDto groupeCompetenceDto, GroupeCompetence groupeCompetence) {
        if(!groupeCompetenceDto.getTags().isEmpty()) {
            groupeCompetenceDto.getTags().forEach(tagDto -> {
                if (!TagValidator.validate(tagDto).isEmpty()){
                    throw new InvalidEntityException("Le libelle du tag ne doit pas être vide", ErrorCodes.TAG_NOT_VALID, TagValidator.validate(tagDto));
                }
                else {
                    if(tagRepository.findByLibelleAndArchiveFalse(tagDto.getLibelle()).isPresent()) {
                        groupeCompetence.addTag(tagRepository.findByLibelleAndArchiveFalse(tagDto.getLibelle()).get());
                        groupeCompetence.getTags().remove(TagDto.toEntity(tagDto));
                    }
                }
            });
        }
    }
}