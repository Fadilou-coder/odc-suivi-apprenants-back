package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.PromoDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.AdminRepository;
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
    AdminRepository adminRepository;
}
