package com.FitnessTrack.MyFitnessTrack.integresionTest;

import com.FitnessTrack.MyFitnessTrack.model.dto.PersonDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateCustomer_ShouldNotPass() throws Exception {
        PersonDto personDto = PersonDto.builder()
                .name("max")
                .username("max123")
                .dateOfBirth(LocalDate.of(2012,1,24))
                .sex("m")
                .weight(68.4)
                .height(187.2)
                .objective("cutting")
                .activity("moderate")
                .build();

        mockMvc.perform(post("/usersList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(personDto)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testCreateCustomer_ShouldPass() throws Exception {
        PersonDto personDto = PersonDto.builder()
                .name("max")
                .username("max123")
                .dateOfBirth(LocalDate.of(1998,1,24))
                .sex("m")
                .weight(68.4)
                .height(187.2)
                .objective("cutting")
                .activity("moderate")
                .build();

        mockMvc.perform(post("/usersList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(personDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetCustomer_ShouldPass() throws Exception {
        List<PersonDto> personDto = List.of(
                PersonDto.builder()
                        .name("max")
                        .username("max123")
                        .dateOfBirth(LocalDate.of(2012,1,24))
                        .sex("m")
                        .weight(68.4)
                        .height(187.2)
                        .objective("cutting")
                        .activity("moderate")
                        .build(),
                PersonDto.builder()
                        .name("user1")
                        .username("urn1")
                        .dateOfBirth(LocalDate.of(1998,1,1))
                        .sex("f")
                        .weight(75.5)
                        .height(165.0)
                        .objective("bulking")
                        .activity("moderate")
                        .build()
        );


        mockMvc.perform(get("/usersList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(personDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String objectToString ( Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }
}
