package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Apprenant extends AppUser {
    private String matricule;
    private String etat;
    private String role;
    private String motif;
    private String genre;
    private String niveauEntree;
    private String handicap;
    private String orphelin;
    private String etablissementPrecedent;



    public Apprenant(
            String username, String password,
            String prenom, String nom, String email,
            String cni, String adresse, String numeroTelephone,
            String etat, String matricule, String role, LocalDate dateNaissance, String motif, String genre, String niveauEntree, String handicap, String orphelin, String etablissementPrecedent) {
        super(username, password, prenom, nom, email, cni, adresse, numeroTelephone,dateNaissance);
        this.etat = etat;
        this.matricule = matricule;
        this.role = role;
        this.motif = motif;
        this.genre = genre;
        this.niveauEntree = niveauEntree;
        this.handicap = handicap;
        this.orphelin = orphelin;
        this.etablissementPrecedent = etablissementPrecedent;

    }

    @OneToMany(mappedBy = "apprenant")
    private Collection<DocumentApprenant> docs;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Pret> prets;

    @ManyToMany(mappedBy = "apprenants")
    private Collection<ProfilSortie> profilSorties;

    @OneToMany(mappedBy = "apprenant")
    private Collection<CompetenceValide> competenceValides;

    @ManyToMany(mappedBy = "apprenants")
    private Collection<Groupe> groupes;

    @OneToMany(mappedBy = "apprenant")
    private Collection<BriefApprenant> briefApprenants;

    @OneToMany(mappedBy = "apprenant")
    private Collection<FilDiscussion> filDiscussions;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Message> messages;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Reponse> reponses;
}
