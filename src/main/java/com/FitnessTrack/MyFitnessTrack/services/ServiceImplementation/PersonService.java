package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.repositories.PersonRepository;
import com.FitnessTrack.MyFitnessTrack.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonServices {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    private void entityToDto(List<PersonDto> userDtos, Person users) {
        PersonDto usersDto = new PersonDto();
        usersDto.setId(users.getId());
        usersDto.setName(users.getName());
        usersDto.setLastname(users.getLastname());
        usersDto.setUsername(users.getUsername());
        usersDto.setDateOfBirth(users.getDateOfBirth());
        usersDto.setSex(String.valueOf(users.getSex()));
        usersDto.setWeight(users.getWeight());
        usersDto.setHeight(users.getHeight());
        usersDto.setBodyFat(users.getBodyFat());

        //set age from date of birth
        int age = Period.between(usersDto.getDateOfBirth(), LocalDate.now()).getYears();
        if (usersDto.getDateOfBirth() == null) {
            usersDto.setAge(0);
            return;
        } else {
            usersDto.setAge(age);
        }

        userDtos.add(usersDto);
    }

    @Override
    public List<PersonDto> findAllUsers() {
        List<PersonDto> usersDtoList = new ArrayList<>();
        repository.findAll().forEach(users -> entityToDto(usersDtoList, users));
        return usersDtoList;
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    public boolean setAgeFromDateOfBirth(Person user) {
        if (user.getDateOfBirth() == null) {
            user.setAge(0);
            return true;
        }

        int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 15){
            return true;
        }
        user.setAge(age);
        return false;
    }

    @Override
    public Person addUser(Person person) {
        char gender = Character.toLowerCase(person.getSex());
        if (gender != 'm' && gender != 'f') {
            throw new RuntimeException("Please enter your gender as 'M' or 'F'");
        } else if (setAgeFromDateOfBirth(person)) {
            throw new RuntimeException("Legal age for this app is 16 or more");
        } else {
            return repository.save(person);
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Person updatePerson(Long id, String username, Double weight, Double height, BigDecimal bodyFat) {
        Person user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));

        if (username != null) {
            user.setUsername(username);
        }
        if (weight != null) {
            user.setWeight(weight);
        }
        if (height != null) {
            user.setHeight(height);
        }
        if (bodyFat != null) {
            user.setBodyFat(bodyFat);
        }

        // Recalculate age
        if (user.getDateOfBirth() != null) {
            int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
            user.setAge(age);
        }

        return repository.save(user);
    }

}
