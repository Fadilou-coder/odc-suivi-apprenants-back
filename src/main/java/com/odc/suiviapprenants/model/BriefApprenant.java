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

    @ManyToOne
    private Apprenant apprenant;

    @OneToMany(mappedBy = "briefApprenant")
    private Collection<LivrablePartiel> livrablePartiels;

    @OneToOne
    private FilDiscussion filDiscussion;

    private boolean valide;
}
