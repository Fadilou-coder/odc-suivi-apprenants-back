package com.odc.suiviapprenants.service;

import com.odc.suiviapprenants.entity.Admin;
import com.odc.suiviapprenants.entity.Apprenant;
import com.odc.suiviapprenants.entity.User;

public interface ApplicationService {
    public User findUserByUsername(String username);
    public Admin findUserByUsernameAdmin(String username);
    public Apprenant findAppByUsername(String username);
}
