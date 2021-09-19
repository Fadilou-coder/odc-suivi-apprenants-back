package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.GroupeTag;
import com.odc.suiviapprenants.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface GroupeTagRepository extends JpaRepository<GroupeTag, Long> {
    List<GroupeTag> findAllByArchiveFalse();
    Optional<GroupeTag> findByLibelleAndArchiveFalse(String libelle);
    Optional<GroupeTag> findByIdAndArchiveFalse(Long id);

    List<GroupeTag> findAllByTagsId(Long id);
}
