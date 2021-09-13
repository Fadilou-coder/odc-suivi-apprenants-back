package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity {

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

    public User(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone) {
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.cni = cni;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
    }
}
