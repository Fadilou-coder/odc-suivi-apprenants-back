package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity
@Data
public class Livrable extends AbstractEntity{
    private String url;

    @ManyToOne(cascade = CascadeType.MERGE)
    private LivrableAttendu livrableAttendu;

    @ManyToOne(cascade = CascadeType.MERGE)
    private  BriefApprenant briefApprenant;
}
