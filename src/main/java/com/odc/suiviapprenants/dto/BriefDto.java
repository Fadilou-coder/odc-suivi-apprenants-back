package com.odc.suiviapprenants.dto;


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
    private Collection<LivrableAttendu> livrableAttendus;

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
                .livrableAttendus(brief.getLivrableAttendus())
                .build();
    }

    public static Brief toEntity(BriefDto briefDto){
        if (briefDto == null) return null;
        Brief brief = new Brief();
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
        brief.setTags(briefDto.getTags().stream().map(TagDto::toEntity).collect(Collectors.toList()));
        brief.setRessources(briefDto.getRessources().stream().map(RessourcesDto::toEntity).collect(Collectors.toList()));
        brief.setFormateur(FormateurDto.toEntity(briefDto.getFormateur()));
        brief.setPromo(PromoDto.toEntity(briefDto.getPromo()));
        brief.setLivrableAttendus(briefDto.getLivrableAttendus());

        return brief;
    }


}
