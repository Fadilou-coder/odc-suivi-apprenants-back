package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.ProfilSortieDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.repository.ProfilSortieRepository;
import com.odc.suiviapprenants.service.ProfileSortieService;
import com.odc.suiviapprenants.validator.ProfileSortieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProfileSortieImp implements ProfileSortieService {

    @Autowired
    ProfilSortieRepository profilSortieRepository;
    @Override
    public ProfilSortieDto save(ProfilSortieDto profilSortieDto) {
        List<String> errors = ProfileSortieValidator.validate(profilSortieDto);

        if(profilSortieAlreadyExists(profilSortieDto.getLibelle())) {
            throw new InvalidEntityException("Un autre profile de sortie avec le meme libelle existe deja", ErrorCodes.PROFIL_SORTIE_ALREADY_IN_USE,
                    Collections.singletonList("Un autre profile de sortie avec le meme libelle existe deja dans la BDD"));
        }

        if (!errors.isEmpty()) {
            log.error("ProfileSortie is not valid {}", profilSortieDto);
            throw new InvalidEntityException("Le profile de sortie n'est pas valide", ErrorCodes.PROFIL_SORTIE_NOT_VALID, errors);
        }

        return ProfilSortieDto.fromEntity(
                profilSortieRepository.save(
                        ProfilSortieDto.toEntity(profilSortieDto)
                )
        );
    }

    @Override
    public List<ProfilSortieDto> findAll() {
        return profilSortieRepository.findAllByArchiveFalse().stream()
                .map(ProfilSortieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProfilSortieDto findById(Long id) {
        if (id == null) {
            log.error("ProfileSortie ID is null");
            return null;
        }

        return profilSortieRepository.findById(id).map(ProfilSortieDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Profile de sortie avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PROFIL_SORTIE_NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        ProfilSortie profilSortie = profilSortieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun ProfileSortie avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PROFIL_SORTIE_NOT_FOUND));
        profilSortie.setArchive(true);
        profilSortieRepository.save(profilSortie);
    }

    @Override
    public ProfilSortieDto put(ProfilSortieDto profilSortieDto,Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        ProfilSortie profilSortie = profilSortieRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun ProfileSortie avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.PROFIL_SORTIE_NOT_FOUND));
        profilSortie.setLibelle(profilSortieDto.getLibelle());
        profilSortieRepository.flush();
        return profilSortieDto;
    }

    private boolean profilSortieAlreadyExists(String libelle) {
        Optional<ProfilSortie> profilSortie = profilSortieRepository.findByLibelle(libelle);
        return profilSortie.isPresent();
    }

}
