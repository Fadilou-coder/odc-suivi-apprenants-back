package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ApplicationServiceImpl implements ApplicationService {
    UserRepository userRepository;
    PromoRepository promoRepository;
    AdminRepository adminRepository;
    ApprenantRepository apprenantRepository;
    ReferentielRepository referentielRepository;
    FormateurRepository formateurRepository;


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
    public Formateur findFormateurByUsername(String username) {
        if (formateurRepository.findByUsernameAndArchiveFalse(username).isPresent()) {
            return formateurRepository.findByUsernameAndArchiveFalse(username).get();
        }
        return null;
    }
}
