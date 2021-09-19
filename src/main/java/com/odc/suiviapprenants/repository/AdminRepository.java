package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsernameAndArchiveFalse(String username);

    List<Admin> findAllByRoleId(Long id);

    List<Admin> findAllByArchiveFalse();

    Optional<Admin> findByIdAndArchiveFalse(Long id);
}
