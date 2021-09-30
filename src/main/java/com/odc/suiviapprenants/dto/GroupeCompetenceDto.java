package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.GroupeCompetence;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GroupeCompetenceDto {
    private Long id;
    private String libelle;
    private String description;

    List<CompetenceDto> competences;
    List<TagDto> tags;

    public static GroupeCompetenceDto fromEntity(GroupeCompetence groupeCompetence) {
        if(groupeCompetence == null) return null;

        return GroupeCompetenceDto.builder()
                .id(groupeCompetence.getId())
                .libelle(groupeCompetence.getLibelle())
                .description(groupeCompetence.getDescription())
                .competences(
                        groupeCompetence.getCompetences() != null ?
                                groupeCompetence.getCompetences().stream()
                                        .map(CompetenceDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .tags(
                        groupeCompetence.getTags() != null ?
                                groupeCompetence.getTags().stream()
                                        .map(TagDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static GroupeCompetence toEntity(GroupeCompetenceDto groupeCompetenceDto) {
        if(groupeCompetenceDto == null) return null;

        GroupeCompetence groupeCompetence = new GroupeCompetence();
        groupeCompetence.setId(groupeCompetenceDto.getId());
        groupeCompetence.setLibelle(groupeCompetenceDto.getLibelle());
        groupeCompetence.setDescription(groupeCompetenceDto.getDescription());
        groupeCompetence.setCompetences(groupeCompetenceDto.getCompetences().stream().map(CompetenceDto::toEntity).collect(Collectors.toList()));
        groupeCompetence.setTags(groupeCompetenceDto.getTags().stream().map(TagDto::toEntity).collect(Collectors.toList()));

        return groupeCompetence;
    }
}
