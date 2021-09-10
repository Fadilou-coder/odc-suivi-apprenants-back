package com.odc.suiviapprenants.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Competence extends AbstractEntity {

    private String libelle;

    @ManyToMany(mappedBy = "competences")
    private Collection<GroupeCompetence> groupeCompetences;

    @OneToMany(mappedBy = "competence")
    private Collection<NiveauEvaluation> niveauEvaluations;
}
