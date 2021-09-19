package com.odc.suiviapprenants.model;

import lombok.Data;

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
    private Apprenant apprenant;

    @ManyToOne(optional = false)
    private Materiel materiel;
}
