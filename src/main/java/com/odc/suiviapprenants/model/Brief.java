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

    @ManyToMany
    private Collection<Tag> tags;

    @OneToMany(mappedBy = "brief")
    private Collection<Ressource> ressources;

    @ManyToOne
    private Formateur formateur;

    @ManyToOne
    private Promo promo;

    @OneToMany(mappedBy = "brief")
    private Collection<BriefGroupe> briefGroupes;

    @OneToMany(mappedBy = "brief")
    private Collection<BriefApprenant> briefApprenants;

    @OneToMany(mappedBy = "brief")
    private Collection<BriefCompetence> briefCompetences;

    @ManyToMany
    private Collection<LivrableAttendu> livrableAttendus;
}
