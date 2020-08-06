package truc.microservice.movie_service.batch.automation.MovieDelete;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/delete-api/movie-delete.feature",
        tags = {"not @Ignore"})
@ActiveProfiles(value = "test")
public class MovieDeleteTests {
}
