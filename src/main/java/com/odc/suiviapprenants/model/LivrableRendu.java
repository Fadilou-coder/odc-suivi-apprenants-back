package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class LivrableRendu extends AbstractEntity{
    private String statut;
    private LocalDate delai;
    private LocalDate dateRendu;
    private String commentaire;

    @OneToOne(optional = false, cascade = CascadeType.MERGE)
    private LivrablePartiel livrablePartiel;
}
