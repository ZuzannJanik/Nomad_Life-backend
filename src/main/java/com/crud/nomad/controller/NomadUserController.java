package com.crud.nomad.controller;

import com.crud.nomad.domain.NomadUser;
import com.crud.nomad.domain.dto.NomadUserDto;
import com.crud.nomad.exceptions.NomadUserNotFoundException;
import com.crud.nomad.mapper.NomadUserMapper;
import com.crud.nomad.service.NomadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NomadUserController {
    private final NomadUserMapper nomadUserMapper;
    private final NomadUserService service;
    @GetMapping
    public ResponseEntity<List<NomadUserDto>> getUsers() {
        List<NomadUser> nomadUsers = service.getAllNomadUsers();
        return ResponseEntity.ok(nomadUserMapper.mapToUserDtoList(nomadUsers));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<NomadUserDto> getNomadUser(@PathVariable Long userId) throws NomadUserNotFoundException {
        return ResponseEntity.ok(nomadUserMapper.mapToUserDto(service.getNomadUser(userId)));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<NomadUserDto> deleteNomadUser(@PathVariable Long userId) {
        service.deleteNomadUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<NomadUserDto> updateNomadUser(@RequestBody NomadUserDto userDto) {
        NomadUser nomadUser = nomadUserMapper.mapToUser(userDto);
        NomadUser savedNomadUser = service.saveNomadUser(nomadUser);
        return ResponseEntity.ok(nomadUserMapper.mapToUserDto(savedNomadUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody NomadUserDto userDto) {
        service.saveNomadUser(nomadUserMapper.mapToUser(userDto));
        return ResponseEntity.ok().build();
    }
}
