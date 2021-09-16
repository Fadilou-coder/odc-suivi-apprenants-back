package com.odc.suiviapprenants.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Apprenant extends AppUser {

    private String matricule ;

    private String etat;

    private String role;

    private String motif;

    @OneToMany(mappedBy = "apprenant")
    private Collection<DocumentApprenant> docs;

    @OneToMany(mappedBy = "apprenant")
    private Collection<Pret> prets;

    @ManyToMany
    private Collection<ProfilSortie> profilSorties;

    @OneToMany(mappedBy = "apprenant")
    private Collection<CompetenceValide> competenceValides;

}
