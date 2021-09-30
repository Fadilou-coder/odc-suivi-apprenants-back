package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    Optional<Groupe> findByIdAndPromo(Long id, Promo promo);
}
