package truc.microservice.movie_service.batch;

//import com.netflix.discovery.converters.Auto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.batch.service.MovieServiceImpTest;
import truc.microservice.movie_service.batchProcessor.MovieRatingProcessor;
import truc.microservice.movie_service.batchWriter.MovieWriter;
import truc.microservice.movie_service.config.movieConfig.MovieRatingConfiguration;
import truc.microservice.movie_service.controller.MovieController;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.RatingCount;
import truc.microservice.movie_service.service.movie.MovieService;
import truc.microservice.movie_service.service.movieRating.MovieRatingService;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
//import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class MovieBatchTest {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpTest.class);
    private static final String BUCKET_NAME = "test-ahihi";
    private static final String FILE_NAME = "movie.json";

    @MockBean
    MovieController movieController;

    @Autowired
    @Qualifier("movieJob")
    Job movieJob;

    @Autowired
    @Qualifier("jsonItemReader")
    JsonItemReader<MovieRating> jsonItemReader;

    @InjectMocks
    private MovieRatingProcessor movieRatingProcessor = new MovieRatingProcessor();

    @MockBean
    @Qualifier("movieWriter")
    private MovieWriter movieWriter;

    @Mock
    private MovieRatingService movieRatingService;

    @Mock
    private MovieService movieService;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @After
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters()
    {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("bucketName", BUCKET_NAME);
        parametersBuilder.addString("fileName", FILE_NAME);
        parametersBuilder.addDate("date", new Date(), true);
        return parametersBuilder.toJobParameters();
    }

    @Test
    public void whenJobExecute_thenSuccess() throws Exception
    {
        LOGGER.info(">>> Start whenJobExecute_thenSuccess <<<");

        jobLauncherTestUtils.setJob(movieJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());

        System.out.println(jobExecution.getExitStatus());

        Assert.assertEquals(movieJob.getName(), jobExecution.getJobInstance().getJobName());
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

        LOGGER.info(">>> End whenJobExecute_thenSuccess <<<");
    }

    @Test
    public void givenMockedStep_whenReaderCalled_thenSuccess() throws Exception {
        LOGGER.info(">>> Start givenMockedStep_whenReaderCalled_thenSuccess <<<");

        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution(defaultJobParameters());

        StepScopeTestUtils.doInStepScope(stepExecution, () -> {
            jsonItemReader.open(stepExecution.getExecutionContext());

            System.out.println(jsonItemReader.read());
            System.out.println(jsonItemReader.read().getClass());

            Assert.assertEquals(MovieRating.class, jsonItemReader.read().getClass());

            jsonItemReader.close();
            return null;
        });

        LOGGER.info(">>> End givenMockedStep_whenReaderCalled_thenSuccess <<<");
    }

    @Test
    public void givenMovieRating_whenProcessorCalled_thenSuccess() throws Exception {
        LOGGER.info(">>> Start givenMovieRating_whenProcessorCalled_thenSuccess <<<");

        String expectName = "TrucTest";
        double expectAvgStar = (double) 4;
        int idMovie = Mockito.anyInt();

        MovieRating movieRating = new MovieRating();
        movieRating.setIdMovie(idMovie);
        movieRating.setName(expectName);

        RatingCount ratingCount = new RatingCount();
        ratingCount.setAvgStar(expectAvgStar);
        ratingCount.setCount(13);

        Mockito.when(movieRatingService.getMovieRatingCountByIdMovie(idMovie)).thenReturn(ratingCount);

        Movie actualMovie = movieRatingProcessor.process(movieRating);

        Assert.assertEquals(expectName, actualMovie.getName());
        Assert.assertEquals(Double.valueOf(expectAvgStar), Double.valueOf(actualMovie.getAvgStar()));

        LOGGER.info(">>> End givenMovieRating_whenProcessorCalled_thenSuccess <<<");
    }

    @Test
    public void givenMovies_whenWriterCalled_thenSuccess() throws Exception {
        LOGGER.info(">>> Start givenMovies_whenWriterCalled_thenSuccess <<<");

        Mockito.doNothing().when(movieWriter).write(Mockito.anyList());

        movieWriter.write(Mockito.anyList());

        LOGGER.info(">>> End givenMovies_whenWriterCalled_thenSuccess <<<");
    }
}
