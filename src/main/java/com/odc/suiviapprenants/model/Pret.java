package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Pret {
    private @Id @GeneratedValue Long id;

    private Date datePret = new Date();

    @NotBlank(message = "La référence est obligatoire")
    @NotNull(message = "La référence ne peut pas être nul")
    private String reference;

    @NotNull(message = "La convention est obligatoire")
    private byte[] convention;

    private String etat;

    @ManyToOne(optional = false)
    private Apprenant apprenant;

    @ManyToOne(optional = false)
    private Materiel materiel;
}
