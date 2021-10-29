package com.odc.suiviapprenants.service;


import com.odc.suiviapprenants.model.*;

public interface ApplicationService {
    Admin findUserByUsernameAdmin(String username);
    Apprenant findAppByUsername(String username);
    Formateur findFormateurByUsername(String username);
}
