package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
public class GroupeCompetence extends AbstractEntity {

    private String libelle;

    private String description;

    @ManyToMany
    private Collection<Competence> competences;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Referentiel> referentiels;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Tag> tags;






}
