package com.odc.suiviapprenants.repository;
import com.odc.suiviapprenants.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmailAndIdNot(String email, Long id);
    Optional<AppUser> findByNumeroTelephoneAndIdNot(String num, Long id);
    Optional<AppUser> findByUsernameAndIdNot(String username, Long id);
    Optional<AppUser> findByCniAndIdNot(String cni, Long id);

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByNumeroTelephone(String num);
    Optional<AppUser> findByCni(String cni);

}
