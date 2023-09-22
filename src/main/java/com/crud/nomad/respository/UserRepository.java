package com.crud.nomad.respository;

import com.crud.nomad.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long userId);

    @Override
    void deleteById(Long userId);
}
