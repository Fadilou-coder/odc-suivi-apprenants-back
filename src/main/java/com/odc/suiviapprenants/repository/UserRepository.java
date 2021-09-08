package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
