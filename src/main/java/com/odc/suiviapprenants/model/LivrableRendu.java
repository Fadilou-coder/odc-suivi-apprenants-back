package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class LivrableRendu extends AbstractEntity{
    private String statut;
    private LocalDate delai;
    private LocalDate dateRendu;
    private String commentaire;

    @ManyToMany(mappedBy = "livrableRendus")
    private Collection<Livrable> livrables;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private LivrablePartiel livrablePartiel;
}
