package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.FilDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FildeDiscutionRepositoty extends JpaRepository<FilDiscussion, Long> {
    List<FilDiscussion> findAllByBriefApprenantId(Long id);
}
