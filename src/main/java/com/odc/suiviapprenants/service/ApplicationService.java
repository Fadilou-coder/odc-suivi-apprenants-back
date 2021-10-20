package com.odc.suiviapprenants.service;


import com.odc.suiviapprenants.model.*;

import java.util.List;

public interface ApplicationService {
    public AppUser findUserByUsername(String username);
    public Admin findUserByUsernameAdmin(String username);
    public Apprenant findAppByUsername(String username);
    public Admin addAdmin(Admin admin);
    public Promo getPromoUserConnected();
}
