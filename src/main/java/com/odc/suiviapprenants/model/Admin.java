package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Admin extends AppUser {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Role role;
    @ManyToMany
    private Collection<Groupe> groupes;
    @ManyToMany
    private Collection<Promo> promos;
    public Admin(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone, Role role) {
        super(username, password, prenom, nom, email, cni, adresse, numeroTelephone);
        this.role = role;
    }

}
