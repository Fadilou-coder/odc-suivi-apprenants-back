package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Materiel;
import com.odc.suiviapprenants.model.Pret;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class PretDto {

    @Id
    private Long id;

    private Date datePret;

    private String reference;

    private byte[] convention;

    private String etat;

    @JsonIgnore
    private Apprenant apprenant;

    private Materiel materiel;

    public static PretDto fromEntity(Pret pret){
        if (pret == null) return null;

        return PretDto.builder()
                .id(pret.getId())
                .datePret(pret.getDatePret())
                .reference(pret.getReference())
                .convention(pret.getConvention())
                .etat(pret.getEtat())
                .apprenant(pret.getApprenant())
                .materiel(pret.getMateriel())
                .build();
    }

    public static Pret toEntity(PretDto pretDto){
        if (pretDto == null) return null;

        Pret pret = new Pret();
        pret.setId(pretDto.getId());
        pret.setDatePret(pretDto.getDatePret());
        pret.setReference(pretDto.getReference());
        pret.setConvention(pretDto.getConvention());
        pret.setEtat(pretDto.getEtat());
        pret.setApprenant(pretDto.getApprenant());
        pret.setMateriel(pretDto.getMateriel());

        return pret;
    }
}
