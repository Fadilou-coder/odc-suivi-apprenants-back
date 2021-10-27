package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pret extends AbstractEntity {

    private Date datePret = new Date();

    private String reference;

    private byte[] convention;

    private String etat;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Apprenant apprenant;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Materiel materiel;

    public Pret(String reference, String etat, Apprenant apprenant, Materiel materiel) {
        this.reference = reference;
        this.etat = etat;
        this.apprenant = apprenant;
        this.materiel = materiel;
    }
}
