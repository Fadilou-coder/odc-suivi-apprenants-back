package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Apprenant> apprenants;
    @ManyToMany
    private Collection<Admin> admins;
    @ManyToOne(cascade = CascadeType.ALL)
    private Promo promo;

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

}
