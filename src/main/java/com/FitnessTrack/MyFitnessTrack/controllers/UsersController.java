package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAllUsers(){
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Person> findByUsername(@PathVariable String username){
        Optional<Person> users = service.findByUsername(username);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> addUser(@RequestBody Person user) {
        Person saveUser = service.addUser(user);
        return ResponseEntity.ok(saveUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        service.deleteUserById(id);
    }

}
