package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.service.FormateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FormateurImpl implements FormateurService {

    FormateurRepository formateurRepository;
    PromoRepository promoRepository;

    @Override
    public PromoDto promoEncours() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Formateur formateur = formateurRepository.findByUsername(username).get();
        return promoRepository.findByEnCoursTrueAndFormateurs(formateur)
                .map(PromoDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Vous etes affecter à aucune promo en cours", ErrorCodes.PROMO_NOT_FOUND)
        );
    }
}
