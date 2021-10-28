package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Formateur extends AppUser{

    private String role;
    @OneToMany(mappedBy="formateur")
    private Collection<Document> documents;

    @ManyToMany
    Collection<Referentiel> referentiels;

    @ManyToMany(mappedBy = "formateurs")
    Collection<Groupe> groupes;

    @ManyToMany(mappedBy = "formateurs")
    Collection<Promo> promos;

    public Formateur(String username, String password, String prenom, String nom, String email, String numeroTelephone, String role)
    {
        super(username, password, prenom, nom, email, numeroTelephone);
        this.role = role;
    }
}
