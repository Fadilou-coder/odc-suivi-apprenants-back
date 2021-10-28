package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupeCompetence extends AbstractEntity {

    private String libelle;

    private String description;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<Competence> competences;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Referentiel> referentiels;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<Tag> tags;

    public void addCompetence(Competence competence){
        this.competences.add(competence);
        competence.getGroupeCompetences().add(this);
    }

    public void removeCompetence(Competence competence) {
        this.competences.remove(competence);
        competence.getGroupeCompetences().remove(this);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getGroupeCompetences().add(this);
    }

    public void removeTag(Tag tag){
        this.tags.remove(tag);
        tag.getGroupeCompetences().remove(this);
    }

    public void removeReferentiel(Referentiel referentiel) {
        this.referentiels.remove(referentiel);
        referentiel.getGroupeCompetences().remove(this);
    }
}
