package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.GroupeTag;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GroupeTagDto {
    private Long id;
    private String libelle;

    List<TagDto> tags;

    public static GroupeTagDto fromEntity(GroupeTag groupeTag) {
        if(groupeTag == null) return null;

        return GroupeTagDto.builder()
                .id(groupeTag.getId())
                .libelle(groupeTag.getLibelle())
                .tags(
                        groupeTag.getTags() != null ?
                                groupeTag.getTags().stream()
                                        .map(TagDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }
    public static GroupeTag toEntity(GroupeTagDto groupeTagDto) {
        if(groupeTagDto == null) return null;

        GroupeTag groupeTag = new GroupeTag();
        groupeTag.setId(groupeTagDto.getId());
        groupeTag.setLibelle(groupeTagDto.getLibelle());

        //do I need to set tags here? How does all this work?
        return groupeTag;
    }
}
