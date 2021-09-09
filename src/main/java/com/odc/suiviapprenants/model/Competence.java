package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Competence extends AbstractEntity {

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @ManyToMany(mappedBy = "competences")
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "competence")
    private Collection<NiveauEvaluation> niveauEvaluations;
}
