package com.odc.suiviapprenants.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class BriefDto {

    private Long id;
    private String titre;
    private String description;
    private String contexte;
    private LocalDate dateEcheance;
    private String modalitePedagodiques;
    private String criterePerformances;
    private String modaliteEvaluations;
    private String statut; //brouillon, enCours, cloturé
    private boolean valide;
    protected byte[] image;
    private Collection<TagDto> tags;
    private Collection<RessourcesDto> ressources;
    private FormateurDto formateur;
    private PromoDto promo;
    @JsonIgnore
    private Collection<BriefGroupeDto> briefGroupes;
    @JsonIgnore
    private Collection<BriefApprenantDto> briefApprenants;
    private Collection<BriefCompetenceDto> briefCompetences;
    private Collection<LivrablesAttendusDto> livrableAttendus;

    public static BriefDto fromEntity(Brief brief){
        if (brief == null) return null;
        return BriefDto.builder()
                .id(brief.getId())
                .titre(brief.getTitre())
                .description(brief.getDescription())
                .contexte(brief.getContexte())
                .dateEcheance(brief.getDateEcheance())
                .modalitePedagodiques(brief.getModalitePedagodiques())
                .criterePerformances(brief.getCriterePerformances())
                .modaliteEvaluations(brief.getModaliteEvaluations())
                .statut(brief.getStatut())
                .valide(brief.isValide())
                .image(brief.getImage())
                .tags(
                        brief.getTags() != null ?
                                brief.getTags().stream()
                                        .map(TagDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .ressources(
                        brief.getRessources() != null ?
                                brief.getRessources().stream()
                                        .map(RessourcesDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .formateur(FormateurDto.fromEntity(brief.getFormateur()))
                .promo(PromoDto.fromEntity((brief.getPromo())))
                .livrableAttendus(
                        brief.getLivrableAttendus() != null ?
                                brief.getLivrableAttendus().stream()
                                        .map(LivrablesAttendusDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .briefGroupes(
                        brief.getBriefGroupes() != null ?
                                brief.getBriefGroupes().stream()
                                        .map(BriefGroupeDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .briefApprenants(
                        brief.getBriefApprenants() != null ?
                                brief.getBriefApprenants().stream()
                                        .map(BriefApprenantDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .briefCompetences(
                        brief.getBriefCompetences() != null ?
                                brief.getBriefCompetences().stream()
                                        .map(BriefCompetenceDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Brief toEntity(BriefDto briefDto){
        if (briefDto == null) return null;
        Brief brief = new Brief();
        brief.setId(briefDto.getId());
        brief.setTitre(briefDto.getTitre());
        brief.setDescription(briefDto.getDescription());
        brief.setContexte(briefDto.getContexte());
        brief.setDateEcheance(briefDto.getDateEcheance());
        brief.setModalitePedagodiques(briefDto.getModalitePedagodiques());
        brief.setCriterePerformances(briefDto.getCriterePerformances());
        brief.setModaliteEvaluations(briefDto.getModaliteEvaluations());
        brief.setStatut(briefDto.getStatut());
        brief.setValide(briefDto.isValide());
        brief.setImage(briefDto.getImage());
        if (briefDto.getTags() != null)
        brief.setTags(briefDto.getTags().stream().map(TagDto::toEntity).collect(Collectors.toList()));
        if (briefDto.getRessources() != null)
        brief.setRessources(briefDto.getRessources().stream().map(RessourcesDto::toEntity).collect(Collectors.toList()));
        brief.setFormateur(FormateurDto.toEntity(briefDto.getFormateur()));
        brief.setPromo(PromoDto.toEntity(briefDto.getPromo()));
        if (briefDto.getLivrableAttendus() != null)
        brief.setLivrableAttendus(briefDto.getLivrableAttendus().stream().map(LivrablesAttendusDto::toEntity).collect(Collectors.toList()));
        if (briefDto.getBriefApprenants() != null)
        brief.setBriefApprenants(briefDto.getBriefApprenants().stream().map(BriefApprenantDto::toEntity).collect(Collectors.toList()));
        if (briefDto.getBriefGroupes() != null)
        brief.setBriefGroupes(briefDto.getBriefGroupes().stream().map(BriefGroupeDto::toEntity).collect(Collectors.toList()));
        if (briefDto.getBriefCompetences() != null)
        brief.setBriefCompetences(briefDto.getBriefCompetences().stream().map(BriefCompetenceDto::toEntity).collect(Collectors.toList()));

        return brief;
    }


}
