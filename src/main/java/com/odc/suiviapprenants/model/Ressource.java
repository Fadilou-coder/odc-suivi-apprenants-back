package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Ressource extends AbstractEntity{
    private String titre;
    private String url;
    private byte[] pieceJointe;

    @ManyToOne(optional = false)
    private Brief brief;
}
