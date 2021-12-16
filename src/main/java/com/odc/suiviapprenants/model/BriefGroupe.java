package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BriefGroupe{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Groupe groupe;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Brief brief;

    private boolean valide;
}
