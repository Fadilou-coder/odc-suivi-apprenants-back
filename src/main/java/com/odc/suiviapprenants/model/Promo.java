package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promo extends AbstractEntity {

    private String langue;
    private String title;
    private String description;
    private String lieu;
    private LocalDate dateDebut;
    private LocalDate dateFinProvisoire;
    private LocalDate dateFinReelle;
    private String etat;
    private byte[] avatarPromo;
    private boolean enCours = true;

    @ManyToOne
    private Referentiel referentiel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promo")
    private Collection<Groupe> groupes;

    @ManyToMany(mappedBy = "promo")
    private Collection<ProfilSortie> profilSortie;

    @ManyToMany
    @JsonIgnore
    private Collection<Admin> admins;

    @ManyToMany
    @JsonIgnore
    Collection<Formateur> formateurs;
    public Promo(String langue, String title, String description,
                 String lieu, String etat, Referentiel referentiel, Boolean enCours,
                 Collection<Formateur> formateurs,LocalDate dateDebut,LocalDate dateFinProvisoire,Collection<Admin> admins) {

        this.langue = langue;
        this.title = title;
        this.description = description;
        this.lieu = lieu;
        this.etat = etat;
        this.referentiel = referentiel;
        this.enCours = enCours;
        this.formateurs = formateurs;
        this.dateDebut = dateDebut;
        this.dateFinProvisoire = dateFinProvisoire;
        this.admins = admins;
    }
}
