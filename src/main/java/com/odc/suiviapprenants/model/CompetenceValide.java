package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CompetenceValide extends AbstractEntity {

    private boolean niveau1;
    private boolean niveau2;
    private boolean niveau3;

    @ManyToOne(optional = false)
    private Competence competence;

    @ManyToOne(optional = false)
    private Apprenant apprenant;

}
