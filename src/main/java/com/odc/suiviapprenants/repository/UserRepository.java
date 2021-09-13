package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndIdNot(String email, Long id);
    Optional<User> findByNumeroTelephoneAndIdNot(String num, Long id);
    Optional<User> findByUsernameAndIdNot(String username, Long id);
    Optional<User> findByCniAndIdNot(String cni, Long id);

}
