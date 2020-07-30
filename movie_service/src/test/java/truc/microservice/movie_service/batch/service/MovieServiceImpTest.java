package truc.microservice.movie_service.batch.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import truc.microservice.movie_service.exception.ResourceNotFoundException;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.service.movie.MovieService;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class MovieServiceImpTest {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpTest.class);
    private final String movieName = "TrucNguyen";
    private final int id = 1;

    @Mock
    MovieService movieService;

    @Spy
    List<Movie> movies = new ArrayList<>();

    @Spy
    Movie expectMovie;

    @Before
    public void init(){
        initMovies();
        initMovie();
    }

    public void initMovies()
    {
        movies.add(new Movie("Truc", (double) 5));
        movies.add(new Movie("Nguyen", (double) 4));
    }

    public void initMovie()
    {
        expectMovie = new Movie();
        expectMovie.setId(id);
        expectMovie.setName(movieName);
        expectMovie.setAvgStar((double) 5);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void givenInvalidId_whenFindById_thenThrowResourceNotFoundException() {
        LOGGER.info(">>> Start givenInvalidId_whenFindById_thenThrowResourceNotFoundException <<<");

        int id = Mockito.anyInt();
        Mockito.when(movieService.findById(id)).thenThrow(new ResourceNotFoundException("Not found Movie"));

        Mockito.verify(movieService.findById(id));

        LOGGER.info(">>> End givenInvalidId_whenFindById_thenThrowResourceNotFoundException <<<");
    }

    @Test
    public void givenValidId_whenFindById_thenSuccess() {
        LOGGER.info(">>> Start givenValidId_whenFindById_thenSuccess <<<");


        Mockito.when(movieService.findById(id)).thenReturn(expectMovie);

        Movie actualMovie = movieService.findById(id);

        Assert.assertNotNull(actualMovie);
        Assert.assertEquals(expectMovie, actualMovie);

        LOGGER.info(">>> End givenValidId_whenFindById_thenSuccess <<<");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullMovieInsert_whenInsertMovie_thenThrowIllegalArgumentException() {
        LOGGER.info(">>> Start givenNullMovieInsert_whenInsertMovie_thenThrowIllegalArgumentException <<<");

        Mockito.when(movieService.insertMovie(null)).thenThrow(new IllegalArgumentException("Movie Insert cannot be null"));

        Mockito.verify(movieService.insertMovie(null));

        LOGGER.info(">>> End givenNullMovieInsert_whenInsertMovie_thenThrowIllegalArgumentException <<<");
    }

    @Test
    public void givenValidArgument_whenInsertMovie_thenSuccess() {
        LOGGER.info(">>> Start givenValidArgument_whenInsertMovie_thenSuccess <<<");

        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName(movieName);

        Mockito.when(movieService.insertMovie(movieInsert)).thenReturn(expectMovie);

        Movie actualMovie = movieService.insertMovie(movieInsert);

        Assert.assertEquals(expectMovie, actualMovie);

        LOGGER.info(">>> End givenValidArgument_whenInsertMovie_thenSuccess <<<");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullMovies_whenInsertAll_thenSuccess()
    {
        LOGGER.info(">>> Start givenNullMovies_whenInsertAll_thenSuccess <<<");

        Mockito.doThrow(new IllegalArgumentException()).when(movieService).insertAll(null);

        movieService.insertAll(null);

        LOGGER.info(">>> End givenNullMovies_whenInsertAll_thenSuccess <<<");
    }

    @Test
    public void givenValidArgument_whenInsertAll_thenSuccess()
    {
        LOGGER.info(">>> Start givenValidArgument_whenInsertAll_thenSuccess <<<");

        Mockito.doNothing().when(movieService).insertAll(movies);

        movieService.insertAll(movies);

        LOGGER.info(">>> End givenValidArgument_whenInsertAll_thenSuccess <<<");
    }

}
