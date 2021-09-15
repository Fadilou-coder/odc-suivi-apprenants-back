package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ApprenantRepository apprenantRepository;

    @Autowired
    ReferentielRepository referentielRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
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
}
