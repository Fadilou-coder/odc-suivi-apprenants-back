package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.service.CompetenceService;
import com.odc.suiviapprenants.validator.CompetenceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompetenceServiceImpl implements CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private NiveauEvaluationRepository niveauEvaluationRepository;

    @Override
    public CompetenceDto save(CompetenceDto competenceDto) {
        List<String> errors = CompetenceValidator.validateCompetence(competenceDto);
        if (isExistLibelleCompetence(competenceDto.getLibelle()))
        {
            throw new InvalidEntityException("Competence existe deja", ErrorCodes.COMPETENCE_ALREADY_IN_USE, errors);
        }
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("competence n'est pas valide", ErrorCodes.COMPETENCE_NOT_VALID, errors);
        }

        return CompetenceDto.fromEntity(
                competenceRepository.save(
                        CompetenceDto.toEntity(competenceDto)
                )
        );
    }
    @Override
    public List<CompetenceDto> findAll() {
        return competenceRepository.findAllByArchiveFalse().stream()
                .map(CompetenceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CompetenceDto findById(Long id) {
        if (id == null) {
            log.error("Competence ID is null");
            return null;
        }

        return competenceRepository.findById(id)
                .map(CompetenceDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Competence  avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCodes.COMPETENCE_NOT_FOUND)
                );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Competence ID is null");
        }

        Competence competence = competenceRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Competence avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.COMPETENCE_NOT_FOUND));
        competence.setArchive(true);
        competence.getNiveauEvaluations().forEach(niveauEvaluation -> {
            niveauEvaluation.setCompetence(null);
        });
        competenceRepository.flush();

    }

    @Override
    public CompetenceDto put(CompetenceDto competenceDto, Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        Competence competence = competenceRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Competence avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.COMPETENCE_NOT_FOUND));
        if (StringUtils.hasLength(competenceDto.getLibelle())){

            competence.setLibelle(competenceDto.getLibelle());
        }
        competenceRepository.flush();
        return CompetenceDto.fromEntity(competence);
    }

    @Override
    public CompetenceDto affectedNiveau(CompetenceDto competenceDto) {
        Competence competence = competenceRepository.findById(competenceDto.getId()).get();
        validate(competenceDto,competence);
        return CompetenceDto.fromEntity(competence);
    }

    static void ifIssetValue(NiveauEvaluationDto niveauEvaluationDto, NiveauEvaluation niveauEvaluation) {

        if (StringUtils.hasLength(niveauEvaluationDto.getLibelle()))
        {
            niveauEvaluation.setLibelle(niveauEvaluationDto.getLibelle());
        }
        if (StringUtils.hasLength(niveauEvaluationDto.getCritereEvaluation()))
        {
            niveauEvaluation.setCritereEvaluation(niveauEvaluationDto.getCritereEvaluation());
        }
        if (StringUtils.hasLength(niveauEvaluationDto.getGroupeAction()))
        {
            niveauEvaluation.setGroupeAction(niveauEvaluationDto.getGroupeAction());
        }
    }

    public void validate(CompetenceDto competenceDto, Competence competence)
    {

        if (!competenceRepository.findById(competenceDto.getId()).isPresent())
        {
            throw new InvalidEntityException("Cette Competence n'existe pas dans la BD veuillez la creer"
                    ,ErrorCodes.COMPETENCE_NOT_FOUND);
        }

        if (competenceDto.getNiveauEvaluations().toArray().length > 3 | competenceDto.getNiveauEvaluations().toArray().length < 3 )
        {
            throw new InvalidEntityException("Vous ne pouvez mettre que trois niveau dans une competence");
        }

        for (NiveauEvaluationDto niveauEvaluationDto : competenceDto.getNiveauEvaluations()) {
            Long id = niveauEvaluationDto.getId();

            if (!niveauEvaluationRepository.findById(id).isPresent())
            {
                throw new InvalidEntityException("Veuillez reverifier peut etre l'ID "+ id +" est : null ou n'existe pas dans la BDD "
                        ,ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND);
            }
            NiveauEvaluation niveauEvaluation = niveauEvaluationRepository.findById(id).get();

            if (niveauEvaluationRepository.findById(competenceDto.getNiveauEvaluations().get(0).getId()).get().getReferentiel() != niveauEvaluationRepository.findById(competenceDto.getNiveauEvaluations().get(1).getId()).get().getReferentiel() ||
                    niveauEvaluationRepository.findById(competenceDto.getNiveauEvaluations().get(1).getId()).get().getReferentiel() != niveauEvaluationRepository.findById(competenceDto.getNiveauEvaluations().get(2).getId()).get().getReferentiel())
            {
                throw new InvalidEntityException("Choisissez des niveaux de meme referentiel"
                        ,ErrorCodes.NIVEAU_IN_SAME_REFERENTIEL);
            }

            if (niveauEvaluation.getCompetence() == null)
            {
                niveauEvaluation.setCompetence(competence);
            }
            else {
                throw new InvalidEntityException("Cet Niveau: "+niveauEvaluation.getLibelle()+" est deja affecter un competence : " +
                        niveauEvaluation.getCompetence().getLibelle(),ErrorCodes.NIVEAU_ALREADY_AFFECTED);
            }
        }
        niveauEvaluationRepository.flush();
    }
    public boolean isExistLibelleCompetence(String libelle){
        return competenceRepository.findByLibelleAndArchiveFalse(libelle).isPresent();
    }
}
