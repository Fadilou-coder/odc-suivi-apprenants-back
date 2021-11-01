package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Referentiel extends AbstractEntity{

    private String libelle;

    private String description;

    private String critereAdmission;

    private String critereEvaluation;

    @Lob
    private byte[] programme;

    @ManyToMany
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "referentiel")
    private Collection<NiveauEvaluation> niveauEvaluations;

    @ManyToMany(mappedBy = "referentiels")
    Collection<Formateur> formateurs;

    public Referentiel(String libelle, String description, String critereAdmission, String critereEvaluation) {
        this.libelle = libelle;
        this.description = description;
        this.critereAdmission = critereAdmission;
        this.critereEvaluation = critereEvaluation;
    }
}
