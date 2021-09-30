package com.odc.suiviapprenants.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odc.suiviapprenants.model.Promo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String referenceAgate;
    private String title;
    private String description;
    private String lieu;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinProvisoir;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinReeelle;
    private String etat;
    private byte[] avatarPromo;
    private ReferentielDto referentiel;
    private Collection<GroupeDto> groupes;
    private Collection<AdminDto> admins;
    private List<String> apprenantsEmail;

    public PromoDto(String langue, String referenceAgate, String title, String description, String lieu, LocalDate dateDebut, LocalDate dateFinProvisoir, LocalDate dateFinReeelle, String etat, byte[] avatarPromo, ReferentielDto referentiel) {
        this.langue = langue;
        this.referenceAgate = referenceAgate;
        this.title = title;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFinProvisoir = dateFinProvisoir;
        this.dateFinReeelle = dateFinReeelle;
        this.etat = etat;
        this.avatarPromo = avatarPromo;
        this.referentiel = referentiel;
    }

    public static PromoDto fromEntity(Promo promo){
        if (promo == null) return null;
       return   PromoDto.builder()
                .id(promo.getId())
                .langue(promo.getLangue())
                .title(promo.getTitle())
                .description(promo.getDescription())
                .lieu(promo.getLieu())
               .referenceAgate(promo.getReferenceAgate())
                .dateDebut(promo.getDateDebut())
                .dateFinProvisoir(promo.getDateFinProvisoir())
                .dateFinReeelle(promo.getDateFinReeelle())
                .etat(promo.getEtat())
                 .avatarPromo(promo.getAvatarPromo())
                .referentiel(ReferentielDto.fromEntity(promo.getReferentiel()))
                .groupes(
                        promo.getGroupes() !=null?
                        promo.getGroupes()
                        .stream()
                        .map(GroupeDto::fromEntity)
                        .collect(Collectors.toList()): null
                        )
                .admins(
                        promo.getAdmins() !=null?
                        promo.getAdmins()
                                .stream().map(AdminDto::fromEntity)
                                .collect(Collectors.toList()) : null
                        ).build();
    }
    public static Promo toEntity(PromoDto promoDto){
        if (promoDto == null) return null;
        Promo promo = new Promo();
        promo.setLangue(promoDto.getLangue());
        promo.setReferenceAgate(promoDto.getReferenceAgate());
        promo.setTitle(promoDto.getTitle());
        promo.setDescription(promoDto.getDescription());
        promo.setLieu(promoDto.getLieu());
        promo.setDateDebut(promoDto.getDateDebut());
        promo.setDateFinProvisoir(promoDto.getDateFinProvisoir());
        promo.setDateFinReeelle(promoDto.getDateFinReeelle());
        promo.setEtat(promoDto.getEtat());
        promo.setAvatarPromo(promoDto.getAvatarPromo());
        promo.setReferentiel(ReferentielDto.toEntity(promoDto.getReferentiel()));
        promo.setGroupes(promo.getGroupes());

        return promo;

    }
}
