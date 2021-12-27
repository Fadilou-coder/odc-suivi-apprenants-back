package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class ProfilSortie extends AbstractEntity{


    private String libelle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection <Promo> promo;

    @ManyToMany
    private Collection <Apprenant> apprenants;

    public ProfilSortie(String libelle, Collection <Promo> promo, Collection <Apprenant> apprenants) {
        this.libelle = libelle;
        this.promo = promo;
        this.apprenants = apprenants;
    }
}
