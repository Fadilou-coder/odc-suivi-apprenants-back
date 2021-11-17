package com.odc.suiviapprenants.dto;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupeDto {
    private Long id;
    private String nomGroupe;
    private String type;
    private String statut;
    private Collection<ApprenantDto> apprenants;
    private Collection<AdminDto> admins;
    private PromoDto promo;


    public static GroupeDto fromEntity(Groupe groupe)
    {
        if (groupe ==null) return null;
        return  GroupeDto.builder()
                .id(groupe.getId())
                .nomGroupe(groupe.getNomGroupe())
                .type(groupe.getType())
                .statut(groupe.getStatut())
                .apprenants(
                        groupe.getApprenants() !=null?
                                groupe.getApprenants()
                                        .stream()
                                        .map(ApprenantDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .admins(
                        groupe.getAdmins()!=null?
                                groupe.getAdmins()
                                        .stream()
                                        .map(AdminDto::fromEntity)
                                        .collect(Collectors.toList()): null)
                .promo(PromoDto.fromEntity(groupe.getPromo())).build();

    }

    public static Groupe toEntity(GroupeDto groupeDto) {
        if (groupeDto == null) return null;
        Groupe groupe = new Groupe();
        groupe.setId(groupeDto.getId());
        groupe.setNomGroupe(groupeDto.getNomGroupe());
        groupe.setStatut(groupeDto.getStatut());
        groupe.setType(groupeDto.getType());
        groupe.setApprenants(
                groupeDto.getApprenants() !=null ?
                        groupeDto.getApprenants()
                                .stream()
                                .map(ApprenantDto::toEntity)
                                .collect(Collectors.toList()
                                ):null
        );
        groupe.setAdmins(
                groupeDto.getAdmins() !=null ?
                        groupeDto.getAdmins()
                                .stream().map(AdminDto::toEntity)
                                .collect(Collectors.toList()) : null
        );
        groupe.setPromo(
                PromoDto.toEntity(groupeDto.getPromo())
        );
        return groupe;
    }


}
