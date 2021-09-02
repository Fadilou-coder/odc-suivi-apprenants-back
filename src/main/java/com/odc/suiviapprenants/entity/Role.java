package com.odc.suiviapprenants.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data @NoArgsConstructor
public class Role {
    @GeneratedValue @Id
    private Long id;

    @NotNull
    private String libelle;
}
