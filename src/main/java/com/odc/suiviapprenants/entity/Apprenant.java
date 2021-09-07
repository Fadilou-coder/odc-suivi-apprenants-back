package com.odc.suiviapprenants.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Apprenant extends User {
    @NotNull(message = "Le matricule ne peut pas être nul")
    private String matricule;

    @NotNull(message = "L'état ne peut pas être nul")
    private String etat;

    @NotNull(message = "Le rôle ne peut pas être nul")
    private String role = "APPRENANT";

    private String motif;
    private byte[] droitImage;
    private byte[] extraitNaissance;
    private byte[] casierJudiciaire;
    private byte[] diplomeBac;
    private byte[] carteIdentité;
    private byte[] certificatVisite;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Pret> prets;

    @ManyToMany(mappedBy = "apprenants")
    private Collection<ProfilSortie> profilSorties;

    @OneToMany(mappedBy = "apprenant")
    private Collection<CompetenceValide> competenceValides;

}
