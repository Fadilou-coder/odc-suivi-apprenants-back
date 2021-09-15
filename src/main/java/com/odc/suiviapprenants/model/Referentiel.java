package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
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

}
