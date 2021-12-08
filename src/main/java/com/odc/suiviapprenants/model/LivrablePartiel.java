package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
public class LivrablePartiel extends AbstractEntity{
    private String libelle;
    private String description;
    private LocalDate delai;
    private String type;

    @OneToOne(mappedBy = "livrablePartiel", cascade = CascadeType.PERSIST)
    private LivrableRendu livrableRendu;

    @ManyToOne(cascade = CascadeType.MERGE)
    private BriefApprenant briefApprenant;
}
