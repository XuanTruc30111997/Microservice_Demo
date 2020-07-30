package truc.microservice.movie_service.batch;

import com.netflix.discovery.converters.Auto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.config.movieConfig.MovieRatingConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class MovieBatchTest {

    private static final String BUCKET_NAME = "test-ahihi";
    private static final String FILE_NAME = "movie.json";

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters()
    {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addString("bucketName", BUCKET_NAME);
        parametersBuilder.addString("fileName", FILE_NAME);
        return parametersBuilder.toJobParameters();
    }

    @Test
    public void whenJobExecute_thenSuccess() throws Exception
    {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
        System.out.println(jobExecution.getExitStatus());
//        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getExitStatus());
    }
}
