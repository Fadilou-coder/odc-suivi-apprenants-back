package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
public class Livrable extends AbstractEntity{
    private String url;

    @ManyToOne
    private LivrableAttendu livrableAttendu;

    @ManyToMany
    private Collection<LivrableRendu> livrableRendus;
}
