package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BriefCompetence {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Brief brief;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Competence competence;

    @ManyToOne(cascade = CascadeType.MERGE)
    private NiveauEvaluation niveau;

    private boolean valide;
}
