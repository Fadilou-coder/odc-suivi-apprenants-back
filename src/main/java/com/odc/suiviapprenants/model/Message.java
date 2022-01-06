package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Message extends AbstractEntity{
    private String libelle;
    private byte[] pieceJointe;

    @ManyToOne(cascade = CascadeType.MERGE)
    private FilDiscussion filDiscussion;

    @ManyToOne
    private Apprenant apprenant;

    @ManyToOne
    private Formateur formateur;
}
