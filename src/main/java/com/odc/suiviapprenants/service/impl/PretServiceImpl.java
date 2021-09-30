package com.odc.suiviapprenants.service.impl;


import com.odc.suiviapprenants.dto.PretDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Materiel;
import com.odc.suiviapprenants.model.Pret;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.MaterielRepository;
import com.odc.suiviapprenants.repository.PretRepository;
import com.odc.suiviapprenants.service.PretService;
import com.odc.suiviapprenants.validator.PretValidator;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PretServiceImpl implements PretService {

    @Autowired
    PretRepository pretRepository;

    @Autowired
    MaterielRepository materielRepository;

    @Autowired
    ApprenantRepository apprenantRepository;


    @Override
    public PretDto save(String reference, String etat, MultipartFile convention, String libelle, String description, String apprenant) throws IOException {

        PretDto pretDto = new PretDto(
                null,
                new Date(),
                reference,
                AdminServiceImpl.compressBytes(convention.getBytes()),
                etat,
                null,
                null
        );

        Apprenant app = apprenantRepository.findByUsernameAndArchiveFalse(apprenant);
        if (app == null) {
            throw new InvalidEntityException("Apprenant est null", ErrorCodes.PRET_NOT_FOUND,
                    Collections.singletonList("Aucun Apprenant avec le username = " + apprenant + " ne se trouve dans la BDD"));
        }

        pretDto.setApprenant(app);
        if (materielRepository.findByLibelleAndAndDescriptionAndArchiveFalse(libelle, description).isPresent()){
                pretDto.setMateriel(materielRepository.findByLibelleAndAndDescriptionAndArchiveFalse(libelle, description).get());
        }else {
            pretDto.setMateriel(new Materiel(libelle, description));
        }

        List<String> errors = PretValidator.validate(pretDto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le pret n'est pas valide", ErrorCodes.PRET_NOT_VALID, errors);
        }

        return PretDto.fromEntity(
                pretRepository.save(
                        PretDto.toEntity(pretDto)
                )
        );
    }

    @Override
    public List<PretDto> findAll() {

        return pretRepository.findAllByArchiveFalse().stream()
                .map(PretDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PretDto findById(Long id) {

        if (id == null) return null;

        return pretRepository.findByIdAndArchiveFalse(id).map(PretDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Pret avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.PRET_NOT_FOUND)
        );
    }

    @Override
    public PretDto put(Long id, String reference, String etat) throws IOException {
        Pret pret = pretRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun pret avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.PRET_NOT_FOUND));

        pret.setReference(reference);
        pret.setEtat(etat);
        PretDto pretDto = PretDto.fromEntity(pret);
        PretValidator.validate(pretDto);
        pretRepository.flush();

        return pretDto;
    }
}
