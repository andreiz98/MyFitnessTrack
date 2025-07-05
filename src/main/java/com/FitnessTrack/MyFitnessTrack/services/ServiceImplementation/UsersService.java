package com.FitnessTrack.MyFitnessTrack.services.ServiceImplementation;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.FitnessTrack.MyFitnessTrack.model.entities.Person;
import com.FitnessTrack.MyFitnessTrack.repositories.UsersRepository;
import com.FitnessTrack.MyFitnessTrack.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService implements UsersServices {

    private final UsersRepository repository;

    @Autowired
    public UsersService(UsersRepository repository) {
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

    @Override
    public Person addUser(Person user) {
        return repository.save(user);
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
