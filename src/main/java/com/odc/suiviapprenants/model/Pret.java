package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Pret extends AbstractEntity {

    private Date datePret = new Date();

    private String reference;

    private byte[] convention;

    private String etat;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Apprenant apprenant;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Materiel materiel;
}
