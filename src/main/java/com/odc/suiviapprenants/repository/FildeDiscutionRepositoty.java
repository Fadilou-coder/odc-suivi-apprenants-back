package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.FilDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FildeDiscutionRepositoty extends JpaRepository<FilDiscussion, Long> {
    Optional<FilDiscussion> findByBriefApprenantId(Long id);
}
