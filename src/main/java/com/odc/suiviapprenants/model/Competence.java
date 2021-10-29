package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competence extends AbstractEntity {

    private String libelle;

    @ManyToMany(mappedBy = "competences")
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "competence")
    private Collection<NiveauEvaluation> niveauEvaluations;

    public Competence(String libelle) {
        this.libelle = libelle;
    }

    @OneToMany(mappedBy = "competence")
    private Collection<BriefCompetence> briefCompetences;
}
