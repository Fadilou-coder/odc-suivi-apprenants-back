package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.ApprenantDto;
import com.odc.suiviapprenants.dto.GroupeDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.GroupeRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.service.GroupeService;
import com.odc.suiviapprenants.validator.GroupeValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class GroupeServiceImpl implements GroupeService {
    GroupeRepository groupeRepository;
    PromoRepository promoRepository;
    ApprenantRepository apprenantRepository;

    @Override
    public GroupeDto save(GroupeDto groupeDto) throws IOException {
        Groupe groupe = new Groupe();
        Optional<Promo> promo = promoRepository.findById(groupeDto.getPromo().getId());
        if (promo.isPresent())
        {
            groupe.setNomGroupe(groupeDto.getNomGroupe());
            groupe.setPromo(promo.get());
            groupe.setStatut("ouvert");
            groupe.setType("");
        }
        else {
            throw new InvalidEntityException("promo n'est pas valide", ErrorCodes.PROMO_NOT_VALID);
        }
        groupeRepository.save(groupe);
        return GroupeDto.fromEntity(groupe);
    }

    @Override
    public List<GroupeDto> findAll() {
        return groupeRepository.findAllByArchiveFalse().stream()
                .map(GroupeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GroupeDto findById(Long id) {
        if (id == null) {
            return null;
        }

        return groupeRepository.findByIdAndArchiveFalse(id).map(GroupeDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Groupe avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_NOT_FOUND)
        );
    }

    @Override
    public List<ApprenantDto> findByApprenantById(Long id) {
        if (id == null) {
            log.error("Groupe ID is null");
            return null;
        }
        return apprenantRepository.findByGroupes(groupeRepository.findById(id).get()).stream().map(ApprenantDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public GroupeDto put(Long id, GroupeDto groupeDto) {
        if (groupeRepository.findByIdAndArchiveFalse(id).isPresent()){
            Groupe groupe = groupeRepository.findById(id).get();
            groupe.removeAllApprenant(groupe.getApprenants());
            if (StringUtils.hasLength(groupeDto.getNomGroupe())){groupe.setNomGroupe(groupeDto.getNomGroupe());}
            if (StringUtils.hasLength(groupeDto.getStatut())){groupe.setStatut(groupeDto.getStatut());}
            if (StringUtils.hasLength(groupeDto.getType())){groupe.setType(groupe.getType());}
            if (groupeDto.getApprenants() != null)
            {
                groupeDto.getApprenants().forEach(apprenantDto -> {
                    Apprenant apprenant = apprenantRepository.findByIdAndArchiveFalse(apprenantDto.getId()).get();
                    groupe.getApprenants().add(apprenant);
                    groupeRepository.flush();
                });
            }

        }else {
            List<String> errors = GroupeValidator.ValidatorGroupe(groupeDto);
            throw new InvalidEntityException("L'id groupe n'est pas valide", ErrorCodes.GROUPE_NOT_VALID, errors);
        }
        return GroupeDto.fromEntity(groupeRepository.findById(id).get());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Groupe ID is null");
        }
        Groupe groupe = groupeRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun Groupe avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.GROUPE_NOT_FOUND));
        groupe.setArchive(true);
        groupe.setPromo(null);
        groupe.removeAllApprenant(groupe.getApprenants());
        groupeRepository.flush();
    }

    @Override
    public List<ApprenantDto> findApprenantNonAffecterByGroupe(Long id) {
        if (groupeRepository.findById(id).isPresent()) {
            Groupe groupe = groupeRepository.findById(id).get();
            Groupe grpPrincipale = groupeRepository.findByNomGroupeAndPromo("GROUPE PRINCIPALE", groupe.getPromo()).get();
            List<Apprenant> apprenantList = new ArrayList<>();
            grpPrincipale.getApprenants().forEach(apprenant -> {
                if (!groupe.getApprenants().contains(apprenant)) {
                    apprenantList.add(apprenant);
                }
            });
            return apprenantList.stream()
                    .map(ApprenantDto::fromEntity)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
