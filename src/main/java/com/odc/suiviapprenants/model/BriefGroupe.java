package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BriefGroupe{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Groupe groupe;

    @ManyToOne
    private Brief brief;

    private boolean valide;
}
