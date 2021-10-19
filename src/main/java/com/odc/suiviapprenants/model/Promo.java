package com.odc.suiviapprenants.model;

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
    private LocalDate dateFinProvisoir;
    private LocalDate dateFinReeelle;
    private String etat;
    private String referenceAgate;
    private byte[] avatarPromo;
    private boolean enCours = true;
    @ManyToOne
    private Referentiel referentiel;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "promo")
    private Collection<Groupe> groupes;

    @ManyToMany
    private Collection<Admin> admins;
}
