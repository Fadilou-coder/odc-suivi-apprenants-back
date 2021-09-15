package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data


public class Apprenant extends AppUser {
    private String matricule;

    private String etat;

    private String role;

    private String motif;

    public Apprenant(String username, String password, String prenom, String nom, String email, String cni, String adresse, String numeroTelephone, String etat, String matricule, String role) {
        super(username, password, prenom, nom, email, cni, adresse, numeroTelephone);
        this.etat = etat;
        this.matricule = matricule;
        this.role = role;
    }

    @OneToMany(mappedBy = "apprenant")
    private Collection<DocumentApprenant> docs;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Pret> prets;

    @ManyToMany
    private Collection<ProfilSortie> profilSorties;

    @OneToMany(mappedBy = "apprenant")
    private Collection<CompetenceValide> competenceValides;

}
