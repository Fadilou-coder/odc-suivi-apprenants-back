package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormateurRepository extends JpaRepository<Formateur,Long> {

    Optional<Formateur> findByUsernameAndArchiveFalse(String username);
    List<Formateur> findAllByArchiveFalse();
    Optional<Formateur> findByIdAndArchiveFalse(Long id);
    Optional<Formateur> findByUsername(String username);
}
