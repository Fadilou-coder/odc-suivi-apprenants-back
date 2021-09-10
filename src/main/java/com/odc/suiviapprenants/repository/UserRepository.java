package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.UserOwner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserOwner, Long> {
    Optional<UserOwner> findByUsername(String username);
    Optional<UserOwner> findByEmail(String email);
    Optional<UserOwner> findByNumeroTelephone(String num);

}
