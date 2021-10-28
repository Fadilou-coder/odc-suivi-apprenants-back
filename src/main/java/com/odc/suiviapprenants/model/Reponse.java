package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Reponse extends AbstractEntity{
    private String libelle;
    private byte[] pieceJointe;
    private int note;

    @ManyToOne
    private Message message;

    @ManyToOne Apprenant apprenant;

    @ManyToOne Formateur formateur;
}
