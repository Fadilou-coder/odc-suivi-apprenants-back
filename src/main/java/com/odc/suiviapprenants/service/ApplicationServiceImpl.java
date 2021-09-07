package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.entity.Admin;
import com.odc.suiviapprenants.entity.Apprenant;
import com.odc.suiviapprenants.entity.User;
import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
