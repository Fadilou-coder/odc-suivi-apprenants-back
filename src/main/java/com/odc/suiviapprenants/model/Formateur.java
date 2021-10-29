package com.odc.suiviapprenants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Formateur extends AppUser{

    private String role;
    @OneToMany(mappedBy = "formateur")
    private Collection<Document> documents;
    @ManyToMany
    Collection<Referentiel> referentiels;
    @ManyToMany
    @JsonIgnore
    Collection<Groupe> groupes;
    @ManyToMany
    Collection<Promo> promos;

    public Formateur(String username, String password, String prenom, String nom, String email, String numeroTelephone, String role)
    {
        super(username, password, prenom, nom, email, numeroTelephone);
        this.role = role;
    }
}
