package com.odc.suiviapprenants.entity;

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
public class User {
    protected @Id @GeneratedValue Long id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @NotNull(message = "Le nom d'utilisateur ne peut pas être nul")
    @Column(unique=true)
    protected String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @NotNull(message = "Le mot de passe ne peut pas être nul")
    protected String password;

    @NotBlank(message = "Le prénom est obligatoire")
    @NotNull(message = "Le prénom ne doit pas être nul")
    protected String prenom;

    @NotBlank(message = "Le nom est obligatoire")
    @NotNull(message = "Le nom ne doit pas être nul")
    protected String nom;

    @NotBlank(message = "L'email est obligatoire")
    @NotNull(message = "L'email ne doit pas être nul")
    @Column(unique=true)
    protected String email;

    @NotBlank(message = "Le CNI est obligatoire")
    @NotNull(message = "Le CNI ne doit pas être nul")
    @Column(unique=true)
    protected String cni;

    @NotBlank(message = "L'adresse est obligatoire")
    @NotNull(message = "L'adresse ne doit pas être nulle")
    protected String adresse;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @NotNull(message = "Le numéro de téléphone ne doit pas être nul")
    @Column(unique=true)
    protected String numeroTelephone;

    @NotBlank(message = "La date de naissance est obligatoire")
    @NotNull(message = "La date de naissance ne doit pas être nulle")
    protected LocalDate dateNaissance;

    @NotBlank(message = "L'avatar est obligatoire")
    @NotNull(message = "L'avatar ne doit pas être nul")
    protected byte[] avatar;

    protected boolean suspended = false;

    protected boolean enabled = true;
}
