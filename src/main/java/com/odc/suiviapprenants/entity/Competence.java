package com.odc.suiviapprenants.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Competence {
    private @Id @GeneratedValue Long id;

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @OneToMany(mappedBy = "competence")
    private Collection<CompetenceValide> competenceValides;

    @ManyToMany(mappedBy = "competences")
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "competence")
    private Collection<NiveauEvaluation> niveauEvaluations;
}
