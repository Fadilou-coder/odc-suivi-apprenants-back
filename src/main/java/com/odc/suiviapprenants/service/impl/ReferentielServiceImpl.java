package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.ReferentielDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.ReferentielService;
import com.odc.suiviapprenants.validator.ReferentielValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferentielServiceImpl implements ReferentielService {

    @Autowired
    ReferentielRepository referentielRepository;

    @Override
    public ReferentielDto save(String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme) throws IOException {

        ReferentielDto referentielDto = new ReferentielDto(
                null,
                libelle,
                description,
                critereAdmission,
                critereEvaluation,
                AdminServiceImpl.compressBytes(programme.getBytes())
        );
        validation(referentielDto);

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
    public ReferentielDto findById(Long id) {
        return referentielRepository.findByIdAndArchiveFalse(id).map(ReferentielDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Référentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.REFERENTIEL_NOT_FOUND)
        );
    }

    @Override
    public ReferentielDto put(Long id, String libelle, String description, String critereEvaluation, String critereAdmission, MultipartFile programme) throws IOException {
        Referentiel referentiel = referentielRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun referentiel avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.ADMIN_NOT_FOUND));

        referentiel.setLibelle(libelle);
        referentiel.setProgramme(AdminServiceImpl.compressBytes(programme.getBytes()));
        referentiel.setDescription(description);
        referentiel.setCritereEvaluation(critereEvaluation);
        referentiel.setCritereAdmission(critereAdmission);

        ReferentielDto referentielDto = ReferentielDto.fromEntity(referentiel);
        validation(referentielDto);

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
        if(userAlreadyExists(referentielDto.getLibelle(), referentielDto.getId())) {
            throw new InvalidEntityException("Un autre referentiel avec le meme libelle existe deja", ErrorCodes.REFERENTIEL_ALREADY_IN_USE,
                    Collections.singletonList("Un autre referenteil avec le meme libelle existe deja dans la BDD"));
        }
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le referentiel n'est pas valide", ErrorCodes.REFERENTIEL_NOT_VALID, errors);
        }
    }

    private boolean userAlreadyExists(String libelle, Long id) {
        Optional<Referentiel> ref;
        if (id == null){
            ref = referentielRepository.findByLibelle(libelle);
        }else {
            ref = referentielRepository.findByLibelleAndIdNot(libelle, id);
        }
        return ref.isPresent();
    }
}