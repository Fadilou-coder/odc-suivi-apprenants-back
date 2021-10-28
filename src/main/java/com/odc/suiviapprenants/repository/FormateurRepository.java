package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur,Long> {
}
