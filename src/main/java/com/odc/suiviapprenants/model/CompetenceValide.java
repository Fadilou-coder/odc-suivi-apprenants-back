package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CompetenceValide extends AbstractEntity {

    @ManyToOne(optional = false)
    private NiveauEvaluation niveau;

    @ManyToOne(optional = false)
    private Competence competence;

    @ManyToOne(optional = false)
    private Apprenant apprenant;

}
