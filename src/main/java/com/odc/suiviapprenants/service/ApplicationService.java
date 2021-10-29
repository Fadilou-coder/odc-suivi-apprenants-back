package com.odc.suiviapprenants.service;


import com.odc.suiviapprenants.model.*;
import com.odc.suiviapprenants.dto.PromoDto;

public interface ApplicationService {
    Admin findUserByUsernameAdmin(String username);
    Apprenant findAppByUsername(String username);
    Formateur findFormateurByUsername(String username);
    PromoDto promoEncours();
}
