package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    Optional<Tag> findByLibelleAndArchiveFalse(String libelle);
}
