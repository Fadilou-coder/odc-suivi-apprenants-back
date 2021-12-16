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
public class Brief extends AbstractEntity{
    private String titre;
    private String description;
    private String contexte;
    private LocalDate dateEcheance = LocalDate.now();
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "brief")
    private Collection<LivrableAttendu> livrableAttendus;

    public Brief(String titre, String description, String contexte, LocalDate dateEcheance, String modalitePedagodiques, String criterePerformances, String modaliteEvaluations, String statut, boolean valide, Formateur formateur, Promo promo) {
        this.titre = titre;
        this.description = description;
        this.contexte = contexte;
        this.dateEcheance = dateEcheance;
        this.modalitePedagodiques = modalitePedagodiques;
        this.criterePerformances = criterePerformances;
        this.modaliteEvaluations = modaliteEvaluations;
        this.statut = statut;
        this.valide = valide;
        this.formateur = formateur;
        this.promo = promo;
    }
}
