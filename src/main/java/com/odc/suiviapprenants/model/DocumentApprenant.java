package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class DocumentApprenant {
    private @Id @GeneratedValue Long id;

    private byte[] file;

    @ManyToOne(optional = false)
    private Apprenant apprenant;
}
