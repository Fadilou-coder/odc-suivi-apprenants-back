package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.CompetenceDto;
import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
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
    private ReferentielRepository referentielRepository;

    @Autowired
    private NiveauEvaluationRepository niveauEvaluationRepository;

    @Override
    public CompetenceDto save(CompetenceDto competenceDto) {
        validdate(competenceDto);
        log.info(competenceDto.toString());
        return CompetenceDto.mapFromEntity(
                competenceRepository.save(Objects.requireNonNull(CompetenceDto.mapToEntity(competenceDto))));
    }
    @Override
    public List<CompetenceDto> findAll() {
        return competenceRepository.findAllByArchiveFalse().stream()
                .map(CompetenceDto::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CompetenceDto findById(Long id) {
        if (id == null) {
            log.error("Competence ID is null");
            return null;
        }

        return competenceRepository.findById(id)
                .map(CompetenceDto::mapFromEntity)
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
        competenceRepository.flush();

    }

    @Override
    public CompetenceDto put(CompetenceDto competenceDto, Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        Competence competence = competenceRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun ProfileSortie avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PROFIL_SORTIE_NOT_FOUND));
        if (StringUtils.hasLength(competenceDto.getLibelle())){

            competence.setLibelle(competenceDto.getLibelle());
        }
        if (!competenceDto.getNiveauEvaluations().isEmpty())
        {
            for (NiveauEvaluationDto niveauEvaluationDto: competenceDto.getNiveauEvaluations())
            {
                NiveauEvaluation niveauEvaluation = niveauEvaluationRepository.findById(niveauEvaluationDto.getId()).get();

                for (NiveauEvaluation niveauEvaluationNew: competence.getNiveauEvaluations())
                {
                    if (!Objects.equals(niveauEvaluationNew.getId(), niveauEvaluationDto.getId()))
                    {
                        throw new EntityNotFoundException("Le niveau d'evaluation avec l'ID:" + niveauEvaluationDto.getId() +
                                " n'appartient pas a ce competence", ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND);
                    }
                }
                ifIssetValue(niveauEvaluationDto, niveauEvaluation);
            }

        }
        competenceRepository.flush();
        return CompetenceDto.mapFromEntity(competence);
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

    public void validdate(CompetenceDto competenceDto)
    {

        if (competenceRepository.findByLibelle(competenceDto.getLibelle()).isPresent())
        {
            throw new InvalidEntityException("Competence existe deja dans le BD"
                    ,ErrorCodes.COMPETENCE_ALREADY_IN_USE);
        }

        for (NiveauEvaluationDto niveauEvaluationDto : competenceDto.getNiveauEvaluations()) {
            Long id = niveauEvaluationDto.getId();

            if (!niveauEvaluationRepository.findById(id).isPresent())
            {
                throw new InvalidEntityException("Veuillez reverifier peut etre l'ID "+ id +" est : null ou n'existe pas dans la BDD "
                        ,ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND);
            }
            NiveauEvaluation niveauEvaluation = niveauEvaluationRepository.findById(id).get();
            if (niveauEvaluation.getCompetence() == null)
            {
                niveauEvaluation.setCompetence(CompetenceDto.mapToEntity(competenceDto));
            }
            else {
                throw new InvalidEntityException("Cet Niveau est deja affecter un competence : " +
                        niveauEvaluation.getCompetence().getLibelle(),ErrorCodes.NIVEAU_ALREADY_AFFECTED);
            }
        }

        List<String> errors = CompetenceValidator.validateCompetence(competenceDto);
        if (!errors.isEmpty()) {
            log.error("competence is not valid {}", competenceDto);
            throw new InvalidEntityException("competence n'est pas valide", ErrorCodes.COMPETENCE_NOT_VALID, errors);
        }
    }
}
