package com.crud.nomad.respository;

import com.crud.nomad.domain.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
        @Override
        List<Answer> findAll();
        @Override
        Optional<Answer> findById(Long countryId);
        @Override
        Answer save(Answer answer);
        @Override
        void deleteById(Long answerId);
    }
