package com.odc.suiviapprenants.model;

import lombok.Data;
import javax.persistence.Entity;

@Entity
@Data
public class ProfilSortie extends AbstractEntity{


    private String libelle;

}
