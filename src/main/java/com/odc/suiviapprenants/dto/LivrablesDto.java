package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Livrable;
import com.odc.suiviapprenants.model.LivrableRendu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class LivrablesDto {

    Long id;
    private Collection<LivrablesRendusDto> livrableRendus;


    public static LivrablesDto fromEntity(Livrable livrable){
        if (livrable == null) return null;
        return LivrablesDto.builder()
                .id(livrable.getId())
                .build();
    }

    public static Livrable toEntity(LivrablesDto livrablesDto){
        if (livrablesDto == null) return null;
        Livrable livrable = new Livrable();
        livrable.setLivrableRendus(livrablesDto.getLivrableRendus().stream().map(LivrablesRendusDto::toEntity).collect(Collectors.toList()));

        return livrable;
    }
}
