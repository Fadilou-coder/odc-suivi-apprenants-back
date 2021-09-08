package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.User;
import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


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
    private PasswordEncoder passwordEncoder;


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Admin findUserByUsernameAdmin(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Apprenant findAppByUsername(String username) {
        return apprenantRepository.findByUsername(username);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}
