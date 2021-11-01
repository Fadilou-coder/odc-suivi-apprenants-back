package com.odc.suiviapprenants.service.impl;


import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationService service;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Formateur formateur = service.finduserbyusernameformateur(username);
        Admin admin = service.findUserByUsernameAdmin(username);
        Apprenant apprenant = service.findAppByUsername(username);
        Formateur formateur = service.findFormateurByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (admin != null) {
            authorities.add(new SimpleGrantedAuthority(admin.getRole().getLibelle()));
            return new User(admin.getUsername(), admin.getPassword(), authorities);
        }else if (apprenant != null){
            authorities.add(new SimpleGrantedAuthority(apprenant.getRole()));
            return new User(apprenant.getUsername(), apprenant.getPassword(), authorities);
        }else if (formateur != null){
            authorities.add(new SimpleGrantedAuthority(formateur.getRole()));
            return new User(formateur.getUsername(), formateur.getPassword(), authorities);
        }
        else
            throw new UsernameNotFoundException(username);
    }


}
