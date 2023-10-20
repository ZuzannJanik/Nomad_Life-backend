package com.crud.nomad.respository;

import com.crud.nomad.domain.NomadUser;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

@Repository
public interface NomadUserRepository extends CrudRepository<NomadUser, Long> {

    @Override
    List<NomadUser> findAll();

    @Override
    NomadUser save(NomadUser nomadUser);

    @Override
    Optional<NomadUser> findById(Long userId);

    @Override
    void deleteById(Long userId);
    Optional<NomadUser> findUserByLogin(String login);
}
