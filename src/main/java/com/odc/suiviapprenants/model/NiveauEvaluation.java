package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NiveauEvaluation extends AbstractEntity {

    private String libelle;

    private String groupeAction;

    private String critereEvaluation;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(nullable=true)
    private Competence competence;

    @ManyToOne(optional = false)
    private Referentiel referentiel;
}
