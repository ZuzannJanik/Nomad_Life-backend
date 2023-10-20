package com.crud.nomad.service;

import com.crud.nomad.domain.User;
import com.crud.nomad.exceptions.UserNotFoundException;
import com.crud.nomad.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public List<User> getAllUsers() {
        return repository.findAll();
    }
    public User saveUser(final User user) {
        return repository.save(user);
    }
    public User getUser(final Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public void deleteUser(final Long userId) {
        repository.deleteById(userId);
    }
    public Optional<User> getUserByLogin(String login){
        return repository.findUserByLogin(login);
    }
}
