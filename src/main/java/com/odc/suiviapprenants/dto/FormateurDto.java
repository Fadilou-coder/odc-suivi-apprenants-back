package com.odc.suiviapprenants.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odc.suiviapprenants.model.Document;
import com.odc.suiviapprenants.model.Formateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormateurDto {
    private Long id;

    private String username;

    private String password;

    private String prenom;

    private String nom;

    private String email;

    private String cni;

    private String adresse;

    private String numeroTelephone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate dateNaissance;

    protected byte[] avatar;

    private String role;
    private Collection<Document> document;

    public static FormateurDto fromEntity(Formateur formateur){
        if (formateur == null){
            return null;
        }
        return FormateurDto.builder()
                .id(formateur.getId())
                .username(formateur.getUsername())
                .prenom(formateur.getPrenom())
                .nom(formateur.getNom())
                .email(formateur.getEmail())
                .cni(formateur.getCni())
                .adresse(formateur.getAdresse())
                .numeroTelephone(formateur.getNumeroTelephone())
                .dateNaissance(formateur.getDateNaissance())
                .avatar(formateur.getAvatar())
               // .document(formateur.getDocuments())
                .role(formateur.getRole())
                .build();
    }

    public static Formateur toEntity(FormateurDto formateurDto){
        if (formateurDto == null) return null;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Formateur formateur = new Formateur();
        formateur.setUsername(formateurDto.getUsername());
        formateur.setPassword(encoder.encode("password"));
        formateur.setPrenom(formateurDto.getPrenom());
        formateur.setNom(formateurDto.getNom());
        formateur.setEmail(formateurDto.getEmail());
        formateur.setNumeroTelephone(formateurDto.getNumeroTelephone());
        formateur.setRole("FORMATEUR");

        return formateur;

    }
}
