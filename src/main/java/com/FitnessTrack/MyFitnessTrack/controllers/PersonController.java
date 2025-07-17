package com.FitnessTrack.MyFitnessTrack.controllers;

import com.FitnessTrack.MyFitnessTrack.ClassMapper;
import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usersList")
public class PersonController {

    private final PersonService service;
    private final ClassMapper mapper;

    @Autowired
    public PersonController(PersonService service, ClassMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAllUsers() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/username")
    public ResponseEntity<Person> findByUsername(@RequestParam String username) {
        Optional<Person> users = service.findByUsername(username);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody PersonDto personDto) {
        Person saveUser = mapper.ToEntity(personDto);
        return ResponseEntity.ok(service.addUser(saveUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        service.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePersonById(@PathVariable Long id,
                                                   @RequestParam(required = false) String username,
                                                   @RequestParam(required = false) Double weight,
                                                   @RequestParam(required = false) Double height
                                                      ){
        return ResponseEntity.ok(service.updatePerson(id,username,weight,height));
    }
}
