package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class NiveauEvaluation {
    private @Id @GeneratedValue Long id;

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @NotBlank(message = "Le groupe d'action est obligatoire")
    @NotNull(message = "Le groupe d'action ne peut pas être nul")
    private String groupeAction;

    @NotBlank(message = "Le critère d'évaluation est obligatoire")
    @NotNull(message = "Le critère d'évaluation ne peut pas être nul")
    private String critereEvaluation;

    @ManyToOne(optional = false)
    private Competence competence;

    @ManyToOne(optional = false)
    private Referentiel referentiel;
}
