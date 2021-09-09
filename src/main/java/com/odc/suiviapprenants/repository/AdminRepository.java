package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsernameAndArchiveFalse(String username);

    List<Admin> findAllByRoleId(Long id);
}
