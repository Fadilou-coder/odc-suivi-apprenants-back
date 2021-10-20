package com.odc.suiviapprenants.service.impl;

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
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Promo getPromoUserConnected() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findByUsernameAndArchiveFalse(auth.getPrincipal().toString()).get();
        Promo promo = promoRepository.findByEnCoursTrueAndArchiveFalseAndAdmins(admin).get();
        return  promo;
    }
}
