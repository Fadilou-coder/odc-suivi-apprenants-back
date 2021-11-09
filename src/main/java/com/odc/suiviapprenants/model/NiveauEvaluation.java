package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiveauEvaluation extends AbstractEntity {

    private String libelle;

    private String groupeAction;

    private String critereEvaluation;

    public NiveauEvaluation(String libelle, String groupeAction, String critereEvaluation, Competence competence, Referentiel referentiel) {
        this.libelle = libelle;
        this.groupeAction = groupeAction;
        this.critereEvaluation = critereEvaluation;
        this.competence = competence;
        this.referentiel = referentiel;
    }

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(nullable=true)
    private Competence competence;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Referentiel referentiel;

    @OneToMany(mappedBy = "niveau")
    private Collection<BriefCompetence> briefCompetences;
}
