package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class Brief extends AbstractEntity{
    private String titre;
    private String description;
    private String contexte;
    private LocalDate dateEcheance;
    private String modalitePedagodiques;
    private String criterePerformances;
    private String modaliteEvaluations;
    private String statut; //brouillon, enCours, cloturé
    private boolean valide;
    @Lob
    protected byte[] image;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<Tag> tags;

    @OneToMany(mappedBy = "brief")
    private Collection<Ressource> ressources;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Formateur formateur;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Promo promo;

    @OneToMany(mappedBy = "brief", cascade = CascadeType.PERSIST)
    private Collection<BriefGroupe> briefGroupes;

    @OneToMany(mappedBy = "brief", cascade = CascadeType.PERSIST)
    private Collection<BriefApprenant> briefApprenants;

    @OneToMany(mappedBy = "brief", cascade = CascadeType.PERSIST )
    private Collection<BriefCompetence> briefCompetences;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<LivrableAttendu> livrableAttendus;
}
