package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Document extends AbstractEntity{
    private String libelle;
    private byte[] justificatif;

    @ManyToOne
    Formateur formateur;
}
