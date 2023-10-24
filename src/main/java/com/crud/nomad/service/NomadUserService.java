package com.crud.nomad.service;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.exceptions.NomadUserNotFoundException;
import com.crud.nomad.respository.NomadUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NomadUserService {
    private final NomadUserRepository repository;
    public List<NomadUser> getAllNomadUsers() {
        return repository.findAll();
    }
    public NomadUser saveNomadUser(final NomadUser nomadUser) {
        return repository.save(nomadUser);
    }
    public NomadUser getNomadUser(final Long userId) throws NomadUserNotFoundException {
        return repository.findById(userId).orElseThrow(NomadUserNotFoundException::new);
    }
    public void deleteNomadUser(final Long userId) {
        repository.deleteById(userId);
    }
}
