package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.NiveauEvaluationDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.exception.InvalidOperationException;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.NiveauEvaluationService;
import com.odc.suiviapprenants.validator.NiveauEvaluationValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class NiveauEvaluationServiceImpl  implements NiveauEvaluationService {
    ReferentielRepository referentielRepository;
    NiveauEvaluationRepository niveauEvaluationRepository;

    @Override
    public NiveauEvaluationDto save(NiveauEvaluationDto niveauEvaluationDto) {

        Long id = niveauEvaluationDto.getReferentiel().getId();
        Referentiel referentiel =  referentielRepository.findById(id).get();
        niveauEvaluationDto.setReferentiel(ReferentielDto.fromEntity(referentiel));
        validation(niveauEvaluationDto);
        return  NiveauEvaluationDto.fromEntity(
                niveauEvaluationRepository.save(
                        NiveauEvaluationDto.toEntity(niveauEvaluationDto)
                )
        );
    }

    @Override
    public List<NiveauEvaluationDto> findAll() {
        return niveauEvaluationRepository.findAllByArchiveFalse()
                .stream().map(NiveauEvaluationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public NiveauEvaluationDto findById(Long id) {
        if (id == null) {
            throw new InvalidOperationException("L'Id est peut etre nulle",ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND);
        }
        return niveauEvaluationRepository.findByIdAndArchiveFalse(id).map(NiveauEvaluationDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Aucun Niveau d'evaluation avec l'ID = " + id + " ne se trouve dans la BDD",
                                ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("User ID is null");
        }

        NiveauEvaluation niveauEvaluation = niveauEvaluationRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Niveau d'evaluation avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND));
        niveauEvaluation.setArchive(true);
        niveauEvaluationRepository.flush();

    }

    @Override
    public NiveauEvaluationDto put(NiveauEvaluationDto niveauEvaluationDto, Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        NiveauEvaluation niveauEvaluation = niveauEvaluationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Niveau d'evaluation avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.NIVEAU_EVALUATION_NOT_FOUND));
        CompetenceServiceImpl.ifIssetValue(niveauEvaluationDto, niveauEvaluation);

        niveauEvaluationRepository.flush();

        return  NiveauEvaluationDto.fromEntity(niveauEvaluation);
    }

    private void validation(NiveauEvaluationDto niveauEvaluationDto)
    {
        List<String> errors = NiveauEvaluationValidator.validateNiveauEvalutaion(niveauEvaluationDto);

        Referentiel referentiel =  referentielRepository.findById(niveauEvaluationDto.getReferentiel().getId()).get();
        if (isExisteGroupeDaction(niveauEvaluationDto.getGroupeAction()) && isExisteCritereEvaluation(niveauEvaluationDto.getCritereEvaluation()))
        {
            throw new InvalidEntityException("Niveau d'evaluation existe deja dans ce referentiel... LIBELLE: " +
                    niveauEvaluationDto.getLibelle(), ErrorCodes.NIVEAU_EVALUATION_ALREADY_IN_USE,
                    Collections.singletonList("Niveau d'evaluation existe deja dans ce referentiel"));
        }

        if (!errors.isEmpty()) {
            log.error("Le niveau d'evaluation is not valid {}", niveauEvaluationDto);
            throw new InvalidEntityException("Le niveau d'evaluation n'est pas valide", ErrorCodes.ADMIN_NOT_VALID, errors);
        }
    }

    public boolean isExisteGroupeDaction(String groupeAction){
        return niveauEvaluationRepository.findByGroupeActionAndArchiveFalse(groupeAction).isPresent();
    }
    public boolean isExisteCritereEvaluation(String critereEvaluation){
        return niveauEvaluationRepository.findByCritereEvaluationAndArchiveFalse(critereEvaluation).isPresent();
    }

}
