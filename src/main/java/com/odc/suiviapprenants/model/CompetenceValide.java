package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CompetenceValide {
    private @Id @GeneratedValue Long id;

    private boolean niveau1;
    private boolean niveau2;
    private boolean niveau3;

    @ManyToOne(optional = false)
    private Competence competence;

    @ManyToOne(optional = false)
    private Apprenant apprenant;

}
