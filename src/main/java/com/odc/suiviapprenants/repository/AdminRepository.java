package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.Admin;
import com.odc.suiviapprenants.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
    Admin findByUsername(String username);
}
