package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class GroupeCompetence extends AbstractEntity {

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @ManyToMany
    private Collection<Competence> competences;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Referentiel> referentiels;

    @ManyToMany(mappedBy = "groupeCompetences")
    private Collection<Tag> tags;






}
