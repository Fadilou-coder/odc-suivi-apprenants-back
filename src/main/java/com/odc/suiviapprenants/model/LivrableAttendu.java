package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
public class LivrableAttendu extends AbstractEntity{
    private String libelle;

    @ManyToMany(mappedBy = "livrableAttendus")
    private Collection<Brief> briefs;

    @OneToMany(mappedBy = "livrableAttendu")
    private Collection<Livrable> livrables;
}
