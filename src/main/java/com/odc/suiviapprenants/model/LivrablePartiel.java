package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class LivrablePartiel extends AbstractEntity{
    private String libelle;
    private String description;
    private LocalDate delai;
    private String type;

    @OneToMany(mappedBy = "livrablePartiel")
    private Collection<LivrableRendu> livrableRendus;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<BriefApprenant> briefApprenants;
}
