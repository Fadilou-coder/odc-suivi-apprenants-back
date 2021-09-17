package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
public class GroupeCompetence extends AbstractEntity {

    private String libelle;

    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Competence> competences;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Referentiel> referentiels;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Tag> tags;

    public void addCompetence(Competence competence){
        this.competences.add(competence);
        competence.getGroupeCompetences().add(this);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getGroupeCompetences().add(this);
    }
}
