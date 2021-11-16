package com.odc.suiviapprenants.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser extends AbstractEntity {

    @Column(unique=true)
    protected String username;

    protected String password;

    protected String prenom;

    protected String nom;

    @Column(unique=true)
    protected String email;

    @Column(unique=true)
    protected String cni;

    protected String adresse;

    @Column(unique=true)
    protected String numeroTelephone;

    protected LocalDate dateNaissance;

    @Lob
    protected byte[] avatar;

    public AppUser(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone,
                   LocalDate dateNaissance) {
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.cni = cni;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.avatar = avatar;
    }

    public AppUser(String username, String password, String prenom, String nom, String email, String numeroTelephone) {
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
    }
}
