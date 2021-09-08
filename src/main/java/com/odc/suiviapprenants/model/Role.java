package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @GeneratedValue @Id
    private Long id;

    @NotNull(message = "le libelle du rôle ne peut pas être null")
    private String libelle;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Collection<Admin> admins;
}
