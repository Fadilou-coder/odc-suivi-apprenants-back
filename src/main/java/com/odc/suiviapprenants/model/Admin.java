package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Admin extends User{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotNull(message = "Le rôle ne peut pas être null")
    private Role role;

    public Admin(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone, Role role) {
        super(username, password, prenom, nom, email, cni, adresse, numeroTelephone);
        this.role = role;
    }
}
