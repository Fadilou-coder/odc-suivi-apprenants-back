package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
