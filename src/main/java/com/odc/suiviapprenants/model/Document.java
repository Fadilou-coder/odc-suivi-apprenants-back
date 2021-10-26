package com.odc.suiviapprenants.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Document extends AbstractEntity{
    String libelle;
    byte[] justificatif;
}
