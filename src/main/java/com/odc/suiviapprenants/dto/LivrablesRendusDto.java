package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.LivrablePartiel;
import com.odc.suiviapprenants.model.LivrableRendu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class LivrablesRendusDto {

    Long id;
    private String statut;
    private LocalDate delai;
    private LocalDate dateRendu;
    private String commentaire;

    @JsonIgnore
    private LivrablesPartielsDto livrablePartiel;

    public static LivrablesRendusDto fromEntity(LivrableRendu livrableRendu){
        if (livrableRendu == null) return null;
        return LivrablesRendusDto.builder()
                .id(livrableRendu.getId())
                .statut(livrableRendu.getStatut())
                .delai(livrableRendu.getDelai())
                .dateRendu(livrableRendu.getDateRendu())
                .commentaire(livrableRendu.getCommentaire())
                .build();
    }

    public static LivrableRendu toEntity(LivrablesRendusDto livrablesRendusDto){
        if (livrablesRendusDto == null) return null;
        LivrableRendu livrableRendu = new LivrableRendu();
        livrableRendu.setId(livrablesRendusDto.getId());
        livrableRendu.setStatut(livrablesRendusDto.getStatut());
        livrableRendu.setDelai(livrablesRendusDto.getDelai());
        livrableRendu.setDateRendu(livrablesRendusDto.getDateRendu());
        livrableRendu.setCommentaire(livrablesRendusDto.getCommentaire());
        if (livrablesRendusDto.getLivrablePartiel() != null)
            livrableRendu.setLivrablePartiel(LivrablesPartielsDto.toEntity(livrablesRendusDto.getLivrablePartiel()));

        return livrableRendu;
    }

}
