package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
public class Materiel extends AbstractEntity {

    private String libelle;

    private String description;

    @OneToMany(mappedBy = "materiel")
    private Collection<Pret> prets;
}
