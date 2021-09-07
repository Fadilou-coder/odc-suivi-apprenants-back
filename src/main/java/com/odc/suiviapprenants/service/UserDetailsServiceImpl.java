package com.odc.suiviapprenants.service;


import com.odc.suiviapprenants.entity.Admin;
import com.odc.suiviapprenants.entity.Apprenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationService applicationService;

    private String role;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = applicationService.findUserByUsernameAdmin(username);
        Apprenant apprenant = applicationService.findAppByUsername(username);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        if (admin == null){
            if (apprenant == null) throw new UsernameNotFoundException(username);
            authorities.add(new SimpleGrantedAuthority((apprenant.getRole())));
            return new User(apprenant.getUsername(), apprenant.getPassword(), authorities);
        }
        authorities.add(new SimpleGrantedAuthority((admin.getRole().getLibelle())));
        return new User(admin.getUsername(), admin.getPassword(), authorities);
    }


}
