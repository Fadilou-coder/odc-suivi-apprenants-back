package com.odc.suiviapprenants.dto;

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

}
