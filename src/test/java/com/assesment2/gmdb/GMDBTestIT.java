package com.assesment2.gmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GMDBTestIT {

    @Autowired
    MockMvc mockMVC;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void checkAddMovies() throws Exception {
        List<MovieDTO> movieDTO = new ArrayList<MovieDTO>();
        movieDTO.add(new MovieDTO("Movie Name 1"));
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
        List<MovieDTO> movieDTO = new ArrayList<MovieDTO>();
        movieDTO.add(new MovieDTO("Movie Name 1"));
        mockMVC.perform(post("/gmdb/movies")
                .content(objectMapper.writeValueAsString(movieDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMVC.perform(get("/gmdb/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].title").value("Movie Name 1"));
    }

    @Test
    public void getMovieDetails() throws Exception {
        List<MovieDTO> movieDTO = convertToDtoClassFromTestMoviesJson();

        mockMVC.perform(post("/gmdb/movies")
                .content(objectMapper.writeValueAsString(movieDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMVC.perform(get("/gmdb/movies/Steel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Steel"));
    }

    @Test
    public void getMovieDetailsForNonExistingTitle() throws Exception {
        List<MovieDTO> movieDTO = convertToDtoClassFromTestMoviesJson();

        mockMVC.perform(post("/gmdb/movies")
                .content(objectMapper.writeValueAsString(movieDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMVC.perform(get("/gmdb/movies/Steel22")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    private List<MovieDTO> convertToDtoClassFromTestMoviesJson() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/java/data/movielist.json");
        return Arrays.asList(mapper.readValue(file, MovieDTO[].class));
    }
}
