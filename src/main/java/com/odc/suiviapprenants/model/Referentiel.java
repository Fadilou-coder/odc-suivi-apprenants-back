package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Referentiel extends AbstractEntity{

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @NotBlank(message = "La description est obligatoire")
    @NotNull(message = "La description ne peut pas être nulle")
    private String description;

    @NotBlank(message = "Les critères d'admission sont obligatoires")
    @NotNull(message = "Les critères d'admission ne peuvent pas être nuls")
    private String critereAdmission;

    @NotBlank(message = "Les critères d'évaluation sont obligatoires")
    @NotNull(message = "Les critères d'admission ne peuvent pas être nuls")
    private String critereEvaluation;

    @NotNull(message = "le programme est obligatoire")
    private byte[] programme;

    @ManyToMany
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "referentiel")
    private Collection<NiveauEvaluation> niveauEvaluations;
}
