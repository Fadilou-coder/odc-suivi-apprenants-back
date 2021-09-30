package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findAllByArchiveFalse(Pageable pageable);
    Role findByLibelle(String libelle);
}
