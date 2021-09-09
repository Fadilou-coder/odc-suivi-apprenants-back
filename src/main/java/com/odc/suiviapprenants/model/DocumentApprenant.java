package com.odc.suiviapprenants.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class DocumentApprenant extends AbstractEntity {

    private byte[] file;

    @ManyToOne(optional = false)
    private Apprenant apprenant;
}
