package com.odc.suiviapprenants.dto;

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
        livrableRendu.setStatut(livrablesRendusDto.getStatut());
        livrableRendu.setDelai(livrablesRendusDto.getDelai());
        livrableRendu.setDateRendu(livrablesRendusDto.getDateRendu());
        livrableRendu.setCommentaire(livrablesRendusDto.getCommentaire());

        return livrableRendu;
    }

}
