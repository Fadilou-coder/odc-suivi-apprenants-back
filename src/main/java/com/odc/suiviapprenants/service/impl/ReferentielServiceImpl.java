package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.GroupeCompetenceDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.ReferentielService;
import com.odc.suiviapprenants.validator.ReferentielValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ReferentielServiceImpl implements ReferentielService {
    ReferentielRepository referentielRepository;
    GroupeCompetenceRepository groupeCompetenceRepository;
    CompetenceRepository competenceRepository;
    NiveauEvaluationRepository niveauEvaluationRepository;

    @Override
    public ReferentielDto save(String libelle, String description,
                               String critereEvaluation, String critereAdmission,
                               MultipartFile programme) throws IOException {

        ReferentielDto referentielDto = new ReferentielDto(
                null,
                libelle,
                description,
                critereAdmission,
                critereEvaluation,
                AdminServiceImpl.compressBytes(programme.getBytes()),
                null
        );

       // handleGrpCompetences(grpCompetences, referentielDto);

        return ReferentielDto.fromEntity(
                referentielRepository.save(
                    ReferentielDto.toEntity(referentielDto)
                )
        );
    }

    @Override
    public List<ReferentielDto> findAll() {
        return referentielRepository.findAllByArchiveFalse().stream()
                .map(ReferentielDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupeCompetenceDto> findGroupeCompetences(Long id) {
        if(!referentielRepository.findByIdAndArchiveFalse(id).isPresent()) {
            throw new EntityNotFoundException(
                    "Aucun Référentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                    ErrorCodes.REFERENTIEL_NOT_FOUND);
        }
        return groupeCompetenceRepository.findAllByReferentielsIdAndArchiveFalse(id).stream().map(GroupeCompetenceDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public GroupeCompetenceDto findOneGroupeCompetence(Long id_referentiel, Long id_groupeCompetence) {
        if(!referentielRepository.findByIdAndArchiveFalse(id_referentiel).isPresent()) {
            throw new EntityNotFoundException(
                    "Aucun Référentiel avec l'ID = " + id_referentiel + " ne se trouve dans la BDD",
                    ErrorCodes.REFERENTIEL_NOT_FOUND);
        }
        if(!groupeCompetenceRepository.findByIdAndArchiveFalse(id_groupeCompetence).isPresent()) {
            throw new EntityNotFoundException(
                    "Aucun groupe de compétence avec l'ID = " + id_groupeCompetence + " ne se trouve dans la BDD",
                    ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND
            );
        }
        return groupeCompetenceRepository.findByIdAndReferentielsId(id_groupeCompetence, id_referentiel).map(GroupeCompetenceDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Ce groupe de compétence n'est pas dans ce référentiel",
                        ErrorCodes.REFERENTIEL_NOT_FOUND)
        );
    }

    @Override
    public ReferentielDto findById(Long id) {
        return referentielRepository.findByIdAndArchiveFalse(id).map(ReferentielDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Référentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.REFERENTIEL_NOT_FOUND)
        );
    }

    @Override
    public ReferentielDto updateReferentiel(Long id, ReferentielDto referentielDto) {
        Referentiel referentiel = referentielRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun referentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.ADMIN_NOT_FOUND));
        referentielDto.getGroupeCompetences().forEach(groupeCompetenceDto -> {
            GroupeCompetence groupeCompetence = groupeCompetenceRepository.findByLibelleAndArchiveFalse(groupeCompetenceDto.getLibelle())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Aucun groupe de competence avec le libelle = " + groupeCompetenceDto.getLibelle() + " ne se trouve dans la BDD",
                            ErrorCodes.GROUPE_COMPETENCE_NOT_FOUND));
            List<GroupeCompetence> groupeCompetenceList = new ArrayList<>();
            groupeCompetenceList.add(groupeCompetence);
            groupeCompetenceDto.getCompetences().forEach(competenceDto -> {
                Competence competence = competenceRepository.findByLibelleAndArchiveFalse(competenceDto.getLibelle())
                                .orElseThrow(()-> new EntityNotFoundException(
                                        "Aucun   competence avec le libelle = " + competenceDto.getLibelle() + " ne se trouve dans la BDD",
                                        ErrorCodes.COMPETENCE_NOT_FOUND));
                competenceDto.getNiveauEvaluations().forEach(niveauEvaluationDto -> {
                    NiveauEvaluation niveauEvaluation = new NiveauEvaluation();
                    niveauEvaluation.setLibelle(niveauEvaluationDto.getLibelle());
                    niveauEvaluation.setCritereEvaluation(niveauEvaluationDto.getCritereEvaluation());
                    niveauEvaluation.setGroupeAction(niveauEvaluationDto.getGroupeAction());
                    niveauEvaluation.setReferentiel(referentiel);
                    niveauEvaluation.setCompetence(competence);
                    niveauEvaluationRepository.save(niveauEvaluation);
                });
                referentiel.addGroupeCompetence(groupeCompetenceList);
            });

        });
        return ReferentielDto.fromEntity(referentiel);
    }

    @Override
    public ReferentielDto put(Long id, String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme, String grpCompetences) throws IOException {
        Referentiel referentiel = referentielRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun referentiel avec l'ID = " + id + " ne se trouve dans la BDD",ErrorCodes.ADMIN_NOT_FOUND));

        referentiel.setLibelle(libelle);
        referentiel.setProgramme(AdminServiceImpl.compressBytes(programme.getBytes()));
        referentiel.setDescription(description);
        referentiel.setCritereEvaluation(critereEvaluation);
        referentiel.setCritereAdmission(critereAdmission);

        ReferentielDto referentielDto = ReferentielDto.fromEntity(referentiel);
        handleGrpCompetences(grpCompetences, referentielDto);

        referentielRepository.flush();
        return referentielDto;
    }

    @Override
    public void delete(Long id) {
        Referentiel referentiel = referentielRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun referentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.REFERENTIEL_NOT_FOUND));
        referentiel.setArchive(true);
        referentielRepository.flush();
    }

    private void validation(ReferentielDto referentielDto) {
        List<String> errors = ReferentielValidator.validate(referentielDto);
        if(refAlreadyExists(referentielDto.getLibelle(), referentielDto.getId())) {
            throw new InvalidEntityException("Un autre referentiel avec le meme libelle existe deja", ErrorCodes.REFERENTIEL_ALREADY_IN_USE,
                    Collections.singletonList("Un autre referenteil avec le meme libelle existe deja dans la BDD"));
        }
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le referentiel n'est pas valide", ErrorCodes.REFERENTIEL_NOT_VALID, errors);
        }
    }

    private boolean refAlreadyExists(String libelle, Long id) {
        Optional<Referentiel> ref;
        if (id == null){
            ref = referentielRepository.findByLibelle(libelle);
        }else {
            ref = referentielRepository.findByLibelleAndIdNot(libelle, id);
        }
        return ref.isPresent();
    }

    private void handleGrpCompetences(String grpCompetences, ReferentielDto referentielDto) {
        List<GroupeCompetenceDto> groupeCompetences= new ArrayList<>();
        for (String g : grpCompetences.split(",")) {
            if (groupeCompetenceRepository.findByLibelleAndArchiveFalse(g).isPresent()){
                groupeCompetences.add(GroupeCompetenceDto.fromEntity(groupeCompetenceRepository.findByLibelleAndArchiveFalse(g).get()));
            }

        }
        referentielDto.setGroupeCompetences(groupeCompetences);
        validation(referentielDto);
    }
}
