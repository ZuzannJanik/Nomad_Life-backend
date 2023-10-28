package com.crud.nomad.controller;

import com.crud.nomad.exceptions.SearchException;
import com.crud.nomad.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AnswerController {
    private final AnswerService service;
    @GetMapping(value = "{question}")
    ResponseEntity<?> getNewSnippet(@PathVariable String question) throws SearchException {
        return ResponseEntity.ok(service.generateSnippet(question));
    }
}

