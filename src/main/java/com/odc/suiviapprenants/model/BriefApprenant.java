package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class BriefApprenant {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Brief brief;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Apprenant apprenant;

    @OneToMany(mappedBy = "briefApprenant", cascade = CascadeType.MERGE)
    private Collection<LivrablePartiel> livrablePartiels;

    @OneToOne(mappedBy = "briefApprenant")
    private FilDiscussion filDiscussion;

    @OneToMany(mappedBy = "briefApprenant")
    private Collection<Livrable> livrables;

    public BriefApprenant(Brief brief, Apprenant apprenant) {
        this.brief = brief;
        this.apprenant = apprenant;
    }

    private boolean valide = false;
}
