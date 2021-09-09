package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByArchiveFalse();
    Role findByLibelle(String libelle);
}
