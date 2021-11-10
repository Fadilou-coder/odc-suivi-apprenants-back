package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
@Data
public class FilDiscussion extends AbstractEntity{
    private String titre;

    @ManyToOne
    private Apprenant apprenant;

    @OneToMany(mappedBy = "filDiscussion")
    private Collection<Message> messages;

    @OneToOne
    private BriefApprenant briefApprenant;
}
