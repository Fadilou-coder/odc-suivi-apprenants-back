package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Livrable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrableRepository extends JpaRepository<Livrable, Long> {
}
