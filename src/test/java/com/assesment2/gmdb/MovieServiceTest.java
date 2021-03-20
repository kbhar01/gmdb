package com.assesment2.gmdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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
}
