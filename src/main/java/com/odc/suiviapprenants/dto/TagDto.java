package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    private Long id;
    private String libelle;

    public static TagDto fromEntity(Tag tag){
        if (tag == null) return null;

        return TagDto.builder()
                .id(tag.getId())
                .libelle(tag.getLibelle())
                .build();
    }
    public static Tag toEntity(TagDto tagDto){
        if (tagDto == null) return null;
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setLibelle(tagDto.getLibelle());
        return tag;
    }
}
