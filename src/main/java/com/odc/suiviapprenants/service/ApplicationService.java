package com.odc.suiviapprenants.service;


import com.odc.suiviapprenants.model.*;

public interface ApplicationService {
    public AppUser findUserByUsername(String username);
    public Admin findUserByUsernameAdmin(String username);
    public Apprenant findAppByUsername(String username);
    public Formateur finduserbyusernameformateur(String username);
    public Admin addAdmin(Admin admin);
    public Promo getPromoUserConnected();
}
