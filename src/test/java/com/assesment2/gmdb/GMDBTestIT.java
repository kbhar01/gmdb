package com.assesment2.gmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GMDBTestIT {

    @Autowired
    MockMvc mockMVC;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void checkAddMovies() throws Exception {

        MovieDTO movieDTO = new MovieDTO("Movie Name 1");
        mockMVC.perform(post("/gmdb/movies")
                .content(objectMapper.writeValueAsString(movieDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void checkIfMoviesExist() throws Exception {

        mockMVC.perform(get("/gmdb/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }

    @Test
    public void addAndListMovies() throws Exception {
        MovieDTO movieDTO = new MovieDTO("Movie Name 1");
        mockMVC.perform(post("/gmdb/movies")
                .content(objectMapper.writeValueAsString(movieDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMVC.perform(get("/gmdb/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].movieName").value("Movie Name 1"));

    }
}
