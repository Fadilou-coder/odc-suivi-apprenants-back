package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groupe extends AbstractEntity {

    private String nomGroupe;
    private String type;
    private String statut;

     /* @ManyToMany*/
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Apprenant> apprenants;

    @ManyToMany
    private Collection<Admin> admins;

    @ManyToMany
    Collection<Formateur> formateurs;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
   /* @ManyToOne*/
    private Promo promo;

    @OneToMany(mappedBy = "groupe")
    @JsonIgnore
    private Collection<BriefGroupe> briefGroupes;

    public void addApprenant(Apprenant apprenant) {
        this.apprenants.add(apprenant);
        apprenant.getGroupes().add(this);
    }

    public void removeAllApprenant(Collection<Apprenant> apprenantList)
    {
        this.apprenants.removeAll(apprenantList);
        apprenants.forEach(apprenant -> {
            apprenant.getGroupes().remove(apprenant);
        });
    }

    public Groupe(String nomGroupe, String type, String statut, Promo promo, Collection<Formateur> formateurs, Collection<Apprenant> apprenants) {
        this.nomGroupe = nomGroupe;
        this.type = type;
        this.statut = statut;
        this.promo = promo;
        this.formateurs = formateurs;
        this.apprenants = apprenants;
    }

}
