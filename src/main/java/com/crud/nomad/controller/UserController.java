package com.crud.nomad.controller;

import com.crud.nomad.domain.User;
import com.crud.nomad.domain.dto.UserDto;
import com.crud.nomad.exceptions.UserNotFoundException;
import com.crud.nomad.mapper.UserMapper;
import com.crud.nomad.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserMapper userMapper;
    private final UserService service;
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUser(userId)));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId)  throws  UserNotFoundException {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

}
