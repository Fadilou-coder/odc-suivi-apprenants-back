package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivrableAttendu extends AbstractEntity{
    private String libelle;

    @ManyToOne
    private Brief brief;

    @OneToMany(mappedBy = "livrableAttendu")
    private Collection<Livrable> livrables;

    public LivrableAttendu(String libelle, Collection<Livrable> livrables) {
        this.libelle = libelle;
        this.livrables = livrables;
    }
}
