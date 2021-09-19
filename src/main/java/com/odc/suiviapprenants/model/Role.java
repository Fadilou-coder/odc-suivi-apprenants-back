package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role extends AbstractEntity {

    private String libelle;

    public Role(String libelle) {
        this.libelle = libelle;
    }

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Collection<Admin> admins;
}
