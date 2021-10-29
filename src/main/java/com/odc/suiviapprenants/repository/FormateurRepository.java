package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormateurRepository extends JpaRepository<Formateur,Long> {
    Optional<Formateur> findByIdAndArchiveFalse(Long id);}
