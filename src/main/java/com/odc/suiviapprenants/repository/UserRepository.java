package com.odc.suiviapprenants.repository;
import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.UserOwner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserOwner, Long> {
    Optional<UserOwner> findByUsername(String username);
    Optional<UserOwner> findByEmailAndIdNot(String email, Long id);
    Optional<UserOwner> findByNumeroTelephoneAndIdNot(String num, Long id);
    Optional<UserOwner> findByUsernameAndIdNot(String username, Long id);
    Optional<UserOwner> findByCniAndIdNot(String cni, Long id);

}
