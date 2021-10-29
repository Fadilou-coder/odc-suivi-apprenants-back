package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Tag extends AbstractEntity {

    private String libelle;

    @ManyToMany(mappedBy = "tags")
    private Collection<GroupeCompetence> groupeCompetences;

    @ManyToMany(mappedBy = "tags")
    private Collection<GroupeTag> groupeTags;

    @ManyToMany(mappedBy = "tags")
    private Collection<Brief> briefs;

    public Tag(String libelle){
        this.libelle = libelle;
    }

    public void removeGroupeTag(GroupeTag groupeTag){
        this.groupeTags.remove(groupeTag);
        groupeTag.getTags().remove(this);
    }
}
