package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class BriefGroupe{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Groupe groupe;

    public BriefGroupe(Groupe groupe, Brief brief) {
        this.groupe = groupe;
        this.brief = brief;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    private Brief brief;

    private boolean valide = false;
}
