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
        usersDto.setObjective(users.getObjective());
        usersDto.setActivity(users.getActivity());
        usersDto.setCalories(users.getCalories());
        usersDto.setProtein(users.getProtein());
        usersDto.setCarbs(users.getCarbs());
        usersDto.setFats(users.getFats());

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

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private Double getActivityMultiplier(String activity) {
        return switch (activity.toLowerCase()) {
            case "sedentary" -> 1.2;
            case "light" -> 1.375;
            case "moderate" -> 1.55;
            case "active" -> 1.725;
            case "very active" -> 1.9;
            default -> 1.2;
        };
    }

    private Double adjustCaloriesForGoal(Double calories, String goal) {
        return switch (goal.toLowerCase()) {
            case "lose weight", "cutting" -> calories - 500;   // Caloric deficit
            case "gain muscle", "bulking" -> calories + 500;   // Surplus
            case "maintain", "maintenance" -> calories;
            default -> calories;
        };
    }

    @Override
    public void setStatsByUsername(Person person) {
        Double weight = person.getWeight();
        Double height = person.getHeight();
        Integer age = person.getAge();
        char gender = Character.toLowerCase(person.getSex());

        double BMR = (gender == 'm')
                ? (10 * weight + 6.25 * height - 5 * age + 5)
                : (10 * weight + 6.25 * height - 5 * age - 161);

        //set calories
        Double setActivity = getActivityMultiplier(person.getActivity());
        Double calories = BMR * setActivity;

        calories = adjustCaloriesForGoal(calories, person.getObjective());

        //set bodyfat
        double LBM = (gender == 'm')
                ? (0.407 * weight + 0.267 * height - 19.2)
                : (0.252 * weight + 0.473 * height - 48.3);

        double bodyFat = (1 - (BMR - 370) / (21.6 * LBM)) * 100;

        if (bodyFat < 0 || bodyFat >=10 || Double.isNaN(bodyFat)){
            bodyFat = 10;
        }

        double protein = (calories * 0.40) / 4; // 4 cal per gram
        double carbs = (calories * 0.20) / 4; // 4 cal per gram
        double fats = (calories * 0.40) / 9; // 9 cal per gram

        person.setCalories(round(calories));
        person.setProtein(round(protein));
        person.setCarbs(round(carbs));
        person.setFats(round(fats));
        person.setBodyFat(bodyFat);
    }

    @Override
    public Person addUser(Person person) {
        isUnderage(person);
        setStatsByUsername(person);
        return repository.save(person);
    }

    public void isUnderage(Person user) {
        if (user.getDateOfBirth() == null) {
            user.setAge(0);
        } else {
            int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
            user.setAge(age);
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Person updatePerson(Long id, String username, Double weight, Double height) {
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

        //recalculate macronutrients
        setStatsByUsername(user);

        // Recalculate age
        if (user.getDateOfBirth() != null) {
            int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
            user.setAge(age);
        }

        return repository.save(user);
    }
}
