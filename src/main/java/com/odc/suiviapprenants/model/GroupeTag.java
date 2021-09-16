package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class GroupeTag extends AbstractEntity {

    private String libelle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Tag> tags;

    public GroupeTag(String libelle){
        this.libelle = libelle;
    }

    public void  removeTag(Tag tag){
        this.tags.remove(tag);
    }
}
