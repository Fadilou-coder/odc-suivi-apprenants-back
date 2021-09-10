package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class NiveauEvaluation extends AbstractEntity {

    private String libelle;

    private String groupeAction;

    private String critereEvaluation;

    @ManyToOne(optional = false)
    private Competence competence;

    @ManyToOne(optional = false)
    private Referentiel referentiel;
}
