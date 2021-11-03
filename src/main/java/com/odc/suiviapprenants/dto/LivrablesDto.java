package com.odc.suiviapprenants.dto;

import com.odc.suiviapprenants.model.Livrable;
import com.odc.suiviapprenants.model.LivrableRendu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
public class LivrablesDto {

    long id;
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
        livrable.setLivrableRendus(null);

        return livrable;
    }
}
