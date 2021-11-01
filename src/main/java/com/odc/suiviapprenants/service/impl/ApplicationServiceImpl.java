package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public Admin findUserByUsernameAdmin(String username) {
        return adminRepository.findByUsernameAndArchiveFalse(username).get();
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
        if (promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateur).isEmpty()){
            throw new InvalidEntityException(formateur.getUsername() +"n'est dans aucun promo", ErrorCodes.APPRENANT_NOT_VALID);
        }
        return promoRepository.findByEnCoursTrueAndArchiveFalseAndFormateurs(formateur).get();
    }
}
