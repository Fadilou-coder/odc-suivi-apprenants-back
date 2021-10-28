package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiveauEvaluation extends AbstractEntity {

    private String libelle;

    private String groupeAction;

    private String critereEvaluation;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(nullable=true)
    private Competence competence;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Referentiel referentiel;
}
