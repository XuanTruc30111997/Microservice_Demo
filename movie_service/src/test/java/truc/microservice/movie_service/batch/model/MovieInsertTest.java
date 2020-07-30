package truc.microservice.movie_service.batch.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import truc.microservice.movie_service.batch.service.MovieServiceImpTest;
import truc.microservice.movie_service.model.MovieInsert;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringRunner.class)
public class MovieInsertTest {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpTest.class);
    private Validator validator;

    @Before
    public void setup()
    {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenNullName_thenThrowConstraintViolationException()
    {
        LOGGER.info(">>> Start whenNullName_thenThrowConstraintViolationException <<<");

        int expectViolationCount = 1;
        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName(null);

        Set<ConstraintViolation<MovieInsert>> violations = validator.validate(movieInsert);

        Assert.assertEquals(expectViolationCount, violations.size());

        LOGGER.info(">>> End whenNullName_thenThrowConstraintViolationException <<<");
    }

    @Test
    public void whenEmptyName_thenThrowConstraintViolationException()
    {
        LOGGER.info(">>> Start whenEmptyName_thenThrowConstraintViolationException <<<");

        int expectViolationCount = 1;
        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName(null);

        Set<ConstraintViolation<MovieInsert>> violations = validator.validate(movieInsert);

        Assert.assertEquals(expectViolationCount, violations.size());

        LOGGER.info(">>> End whenEmptyName_thenThrowConstraintViolationException <<<");
    }

    @Test
    public void whenBlankName_thenThrowConstraintViolationException()
    {
        LOGGER.info(">>> Start whenBlankName_thenThrowConstraintViolationException <<<");

        int expectViolationCount = 1;
        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName(" ");

        Set<ConstraintViolation<MovieInsert>> violations = validator.validate(movieInsert);

        Assert.assertEquals(expectViolationCount, violations.size());

        LOGGER.info(">>> End whenBlankName_thenThrowConstraintViolationException <<<");
    }

    @Test
    public void whenValid_thenCreateMovieInsertInstance()
    {
        LOGGER.info(">>> Start whenValid_thenCreateMovieInsertInstance <<<");

        int expectViolationCount = 0;
        MovieInsert movieInsert = new MovieInsert();
        movieInsert.setName("Truc");

        Set<ConstraintViolation<MovieInsert>> violations = validator.validate(movieInsert);

        Assert.assertEquals(expectViolationCount, violations.size());

        LOGGER.info(">>> End whenValid_thenCreateMovieInsertInstance <<<");
    }

}
