package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;

import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ApplicationServiceImpl implements ApplicationService {
    UserRepository userRepository;
    PromoRepository promoRepository;
    AdminRepository adminRepository;
    FormateurRepository formateurRepository;
    ApprenantRepository apprenantRepository;
    ReferentielRepository referentielRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Admin findUserByUsernameAdmin(String username) {
        if (adminRepository.findByUsernameAndArchiveFalse(username).isPresent()){
            return adminRepository.findByUsernameAndArchiveFalse(username).get();
        }
        return null;
    }

    @Override
    public Apprenant findAppByUsername(String username) {
        return apprenantRepository.findByUsernameAndArchiveFalse(username);
    }

    @Override
    public Formateur finduserbyusernameformateur(String username) {
        return formateurRepository.findByUsernameAndArchiveFalse(username).get();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Promo getPromoUserConnected() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Formateur formateur = formateurRepository.findByUsernameAndArchiveFalse(auth.getPrincipal().toString()).get();
        if (promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateur).isPresent()) {
            throw new InvalidEntityException(formateur.getUsername() + "n'est dans aucun promo", ErrorCodes.APPRENANT_NOT_VALID);
        }
        return promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateur).get();
    }

    public Formateur findFormateurByUsername(String username) {
        if (formateurRepository.findByUsernameAndArchiveFalse(username).isPresent()) {
            return formateurRepository.findByUsernameAndArchiveFalse(username).get();
        }
        return null;
    }

    @Override
    public PromoDto promoEncours() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (formateurRepository.findByUsernameAndArchiveFalse(username).isPresent()) {
            return promoRepository.findByEnCoursTrueAndFormateurs(formateurRepository.findByUsernameAndArchiveFalse(username).get())
                    .map(PromoDto::fromEntity)
                    .orElseThrow(() -> new EntityNotFoundException("Vous etes affecter à aucune promo en cours", ErrorCodes.PROMO_NOT_FOUND)
                    );
        }else if (adminRepository.findByUsernameAndArchiveFalse(username).isPresent()){
            return promoRepository.findByEnCoursTrueAndArchiveFalseAndAdmins(adminRepository.findByUsernameAndArchiveFalse(username).get())
                    .map(PromoDto::fromEntity)
                    .orElseThrow(() -> new EntityNotFoundException("Vous etes affecter à aucune promo en cours", ErrorCodes.PROMO_NOT_FOUND)
                    );
        }
        else{
            AtomicReference<Promo> promo = null;
            apprenantRepository.findByUsernameAndArchiveFalse(username).getGroupes().forEach(groupe -> {
                 promo.set(groupe.getPromo());
            });
            return PromoDto.fromEntity(promo.get());
        }
    }
}
