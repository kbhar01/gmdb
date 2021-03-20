package com.assesment2.gmdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    MovieRepository mockRepository;

    @InjectMocks
    MovieService subject;

    @Test
    void create() {
        MovieDTO movieDTO = new MovieDTO("superman");
        subject.create(movieDTO);

        verify(mockRepository).save(
                new MovieEntity("superman")
        );
    }

    @Test
    void fetchAllWithNoMovie() {
        when(mockRepository.findAll()).thenReturn(
                Collections.emptyList()
        );
        List<MovieDTO> movieList = subject.fetchAll();
       assertEquals(movieList.size(), 0);
    }

    @Test
    void fetchAllWithMoviesAdded() throws Exception {
        when(mockRepository.findAll()).thenReturn(
            this.convertToEntityClassFromTestMoviesJson()
        );
        List<MovieDTO> movieList = subject.fetchAll();
        assertEquals(movieList.size(), 7);
        assertThat(movieList).isEqualTo(
                this.convertToDtoClassFromTestMoviesJson()
        );
    }

    @Test
    void fetchMovieDetailsFromList() throws Exception{
        when(mockRepository.findAll()).thenReturn(
                this.convertToEntityClassFromTestMoviesJson()
        );
        List<MovieDTO> movieList = subject.fetchAll();
        assertEquals(movieList.size(), 7);
        assertEquals(movieList.get(3).title, "Unbreakable");
    }

    private List<MovieEntity> convertToEntityClassFromTestMoviesJson() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        //File file = new File("src/main/java/data/testmovies.json");
        File file = new File("src/main/java/data/movielist.json");
        return Arrays.asList(mapper.readValue(file, MovieEntity[].class));
    }

    private List<MovieDTO> convertToDtoClassFromTestMoviesJson() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        //File file = new File("src/main/java/data/testmovies.json");
        File file = new File("src/main/java/data/movielist.json");
        return Arrays.asList(mapper.readValue(file, MovieDTO[].class));
    }

}

