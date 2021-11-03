package com.odc.suiviapprenants.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.odc.suiviapprenants.model.Promo;
import lombok.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoDto {
    private Long id;
    private String langue;
    private String title;
    private String description;
    private String lieu;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinProvisoire;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinReeelle;
    private String etat;
    private byte[] avatarPromo;
    private ReferentielDto referentiel;
    private List<GroupeDto> groupes;
    private Collection<AdminDto> admins;
    private List<String> apprenantsEmail;
    private List<FormateurDto> formateurs;

    public static PromoDto fromEntity(Promo promo){
        if (promo == null) return null;
       return   PromoDto.builder()
                .id(promo.getId())
                .langue(promo.getLangue())
                .title(promo.getTitle())
                .description(promo.getDescription())
                .lieu(promo.getLieu())
                .dateDebut(promo.getDateDebut())
                .dateFinProvisoire(promo.getDateFinProvisoire())
                .dateFinReeelle(promo.getDateFinReelle())
                .etat(promo.getEtat())
                .avatarPromo(promo.getAvatarPromo())
               .formateurs(
                       promo.getFormateurs()== null ? null:
                       promo.getFormateurs().stream().map(FormateurDto::fromEntity).collect(Collectors.toList())
               )
                .referentiel(ReferentielDto.fromEntity(promo.getReferentiel()))
                .build();
    }
    public static Promo toEntity(PromoDto promoDto) {
        if (promoDto == null) return null;
        Promo promo = new Promo();
        promo.setLangue(promoDto.getLangue());
        promo.setTitle(promoDto.getTitle());
        promo.setDescription(promoDto.getDescription());
        promo.setLieu(promoDto.getLieu());
        promo.setDateDebut(promoDto.getDateDebut());
        promo.setDateFinProvisoire(promoDto.getDateFinProvisoire());
        promo.setDateFinReelle(promoDto.getDateFinReeelle());
        promo.setEtat(promoDto.getEtat());
        promo.setAvatarPromo(promoDto.getAvatarPromo());
        promo.setReferentiel(ReferentielDto.toEntity(promoDto.getReferentiel()));
        promo.setGroupes(
                promoDto.getGroupes() !=null ?
                        promoDto.getGroupes()
                                .stream()
                                .map(GroupeDto::toEntity)
                                .collect(Collectors.toList()) : null
        );

        return promo;

    }
}
