package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Admin extends User{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Role role;

    public Admin(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone, Role role) {
        super(username, password, prenom, nom, email, cni, adresse, numeroTelephone);
        this.role = role;
    }
}
