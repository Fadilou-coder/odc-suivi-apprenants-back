package com.odc.suiviapprenants.service.impl;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.PromoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PromoServiceImpl implements PromoService {

    @Autowired
    PromoRepository promoRepository;
    @Autowired
    ReferentielRepository referentielRepository;
    @Override
    public PromoDto save(
            String langue,
            String referenceAgate,
            String title,
            String description,
            String lieu,
            String dateDebut,
            String dateFinProvisoir,
            String dateFinReelle,
            String etat,
            MultipartFile avatarPromo,
            String referentiel,
            String groupe
    ) throws Exception{
        PromoDto promoDto = new PromoDto(
                null,
                langue,
                referenceAgate,
                title,
                description,
                lieu,
                LocalDate.parse(dateDebut),
                LocalDate.parse(dateFinProvisoir),
                LocalDate.parse(dateFinReelle),
                etat,
                AdminServiceImpl.compressBytes(avatarPromo.getBytes()),
                null,
                null,
                null
        );
        Groupe groupe1 = new Groupe();
        groupe1.setNomGroupe("Groupe_principale");
        groupe1.setType("principale");
        groupe1.setStatut("ouvert");
        return PromoDto.fromEntity(
                promoRepository.save(PromoDto.toEntity(promoDto))
        );
    }

    @Override
    public List<PromoDto> findAll() {
        return promoRepository.findAllByArchiveFalse().stream()
                .map(PromoDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PromoDto findById(Long id) {
        if (id == null) {
            log.error("Promo ID is null");
            return null;
        }

        return promoRepository.findById(id)
                .map(PromoDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun promo  avec l'ID = " + id + " n' ete trouve dans la BDD", ErrorCodes.PROMO_NOT_FOUND)
                );
    }

    @Override
    public PromoDto put(PromoDto promoDto, Long id) {
        return null;
    }
}
