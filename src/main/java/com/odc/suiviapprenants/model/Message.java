package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
public class Message extends AbstractEntity{
    private String libelle;
    private byte[] pieceJointe;

    @ManyToOne
    private FilDiscussion filDiscussion;

    @ManyToOne
    private Apprenant apprenant;

    @OneToMany(mappedBy = "message")
    private Collection<Reponse> reponses;
}
