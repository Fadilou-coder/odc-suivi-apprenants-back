package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
}
