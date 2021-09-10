package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Materiel extends AbstractEntity {

    @NotBlank(message = "Le libelle est obligatoire")
    @NotNull(message = "Le libelle ne peut pas être nul")
    private String libelle;

    @NotBlank(message = "Veuillez renseigner une description")
    private String description;

    @OneToMany(mappedBy = "materiel")
    private Collection<Pret> prets;
}
