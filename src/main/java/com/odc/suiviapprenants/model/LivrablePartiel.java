package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class LivrablePartiel extends AbstractEntity{
    private String libelle;
    private String description;
    private LocalDate delai;
    private String type;

    @OneToOne(mappedBy = "livrablePartiel", cascade = CascadeType.PERSIST)
    private LivrableRendu livrableRendu;

    @ManyToOne(cascade = CascadeType.MERGE)
    private BriefApprenant briefApprenant;

    public LivrablePartiel(String libelle, String description, LocalDate delai, String type) {
        this.libelle = libelle;
        this.description = description;
        this.delai = delai;
        this.type = type;
    }
}
