package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PretRepository extends JpaRepository<Pret, Long> {

    List<Pret> findAllByArchiveFalse();

    Optional<Pret> findByIdAndArchiveFalse(Long id);
}
