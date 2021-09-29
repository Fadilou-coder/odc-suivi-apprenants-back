package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupe extends AbstractEntity {
    private String nomGroupe;
    private String type;
    private  String statut;
    @ManyToMany
    private Collection<Apprenant> apprenants;
    @ManyToMany
    private Collection<Admin> admins;
    @ManyToOne
    private Promo promo;
}
