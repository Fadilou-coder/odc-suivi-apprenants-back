package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilSortie extends AbstractEntity{


    private String libelle;

    @ManyToOne(cascade= CascadeType.MERGE)
    private Promo promo;

}
