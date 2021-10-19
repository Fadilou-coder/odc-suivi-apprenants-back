package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private CompetenceRepository competenceRepository;


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
    public void saveAllAdmin(List<Admin> adminList) {
        adminRepository.saveAll(adminList);
    }

    @Override
    public void saveAllCompetence(List<Competence> competenceList) {competenceRepository.saveAll(competenceList);}

    @Override
    public void saveAllReferentiel(List<Referentiel> referentielList) {referentielRepository.saveAll(referentielList);}

    @Override
    public Promo getPromoUserConnected() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findByUsernameAndArchiveFalse(auth.getPrincipal().toString()).get();
        log.error("admin");
        return  promoRepository.findByEnCoursTrueAndArchiveFalseAndAdmins(admin).get();
    }
}
