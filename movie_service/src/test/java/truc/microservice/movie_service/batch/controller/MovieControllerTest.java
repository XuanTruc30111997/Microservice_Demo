package truc.microservice.movie_service.batch.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import truc.microservice.movie_service.batch.service.MovieServiceImpTest;
import truc.microservice.movie_service.controller.MovieController;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;

@RunWith(SpringRunner.class)
public class MovieControllerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpTest.class);
    private final int idMovie = 1;

    @TestConfiguration
    static class Configuration {
        @Bean
        MovieController movieController() {
            return new MovieController();
        }
    }

    @MockBean
    MovieController movieController;

    @Test
    public void givenInvalidId_whenGetById_then404Error()
    {
        LOGGER.info(">>> Start givenInvalidId_whenGetById_then404Error <<<");

        ResponseEntity<Movie> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Mockito.when(movieController.getbyId(idMovie)).thenReturn(expectedResponse);

        ResponseEntity<Movie> actualResponse = movieController.getbyId(idMovie);

        Assert.assertEquals(expectedResponse, actualResponse);

        LOGGER.info(">>> End givenInvalidId_whenGetById_then404Error <<<");

    }

    @Test
    public void givenValidId_whenGetById_then200()
    {
        LOGGER.info(">>> Start givenValidId_whenGetById_then200 <<<");

        ResponseEntity<Movie> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(movieController.getbyId(idMovie)).thenReturn(expectedResponse);

        ResponseEntity<Movie> actualResponse = movieController.getbyId(idMovie);

        Assert.assertEquals(expectedResponse, actualResponse);

        Assert.assertEquals(expectedResponse, actualResponse);

        LOGGER.info(">>> End givenValidId_whenGetById_then200 <<<");
    }

    @Test
    public void givenInvalidMovieInsert_whenCreate_then400()
    {
        LOGGER.info(">>> Start givenInvalidMovieInsert_whenCreate_then400 <<<");

        ResponseEntity<Object> expectResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Mockito.when(movieController.create(null)).thenReturn(expectResponse);

        ResponseEntity<Object> actualResponse = movieController.create(null);

        Assert.assertEquals(expectResponse, actualResponse);

        LOGGER.info(">>> End givenInvalidMovieInsert_whenCreate_then400 <<<");
    }

    @Test
    public void givenValidMovieInsert_whenCreate_then201()
    {
        LOGGER.info(">>> Start givenValidMovieInsert_whenCreate_then201 <<<");

        String movieName = "TrucNguyen";

        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName(movieName);

        Movie movie = new Movie();
        movie.setName(movieName);

        ResponseEntity<Object> expectResponse = new ResponseEntity<>(movie, HttpStatus.CREATED);
        Mockito.when(movieController.create(movieInsert)).thenReturn(expectResponse);

        ResponseEntity<Object> actualResponse = movieController.create(movieInsert);

        Assert.assertEquals(expectResponse, actualResponse);

        LOGGER.info(">>> End givenValidMovieInsert_whenCreate_then201 <<<");
    }

}
