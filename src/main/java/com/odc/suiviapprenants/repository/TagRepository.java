package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByIdAndArchiveFalse(Long id);
    Optional<Tag> findByLibelleAndArchiveFalse(String libelle);

    List<Tag> findAllByArchiveFalse();
    List<Tag> findAllByGroupeTagsId(Long id);
    List<Tag> findAllByGroupeCompetencesId(Long id);
}
