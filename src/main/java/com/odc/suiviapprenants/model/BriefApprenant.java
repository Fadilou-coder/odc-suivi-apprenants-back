package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class BriefApprenant {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Brief brief;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Apprenant apprenant;

    @ManyToMany(mappedBy = "briefApprenants", cascade = CascadeType.MERGE)
    private Collection<LivrablePartiel> livrablePartiels;

    @OneToOne(mappedBy = "briefApprenant")
    private FilDiscussion filDiscussion;

    @OneToMany(mappedBy = "briefApprenant")
    private Collection<Livrable> livrables;

    private boolean valide;
}
