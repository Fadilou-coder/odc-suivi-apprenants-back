package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BriefCompetence {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Brief brief;

    @ManyToOne
    private Competence competence;

    @ManyToOne
    private NiveauEvaluation niveau;

    private boolean valide;
}
