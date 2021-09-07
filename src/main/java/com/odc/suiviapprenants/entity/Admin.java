package com.odc.suiviapprenants.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
public class Admin extends User{
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Le rôle ne peut pas être null")
    private Role role;
}
