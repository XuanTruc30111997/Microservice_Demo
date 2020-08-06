package truc.microservice.catalog_service.automation.CatalogSearch;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/catalog-api/catalogSearch.feature",
        tags = {"@SmokeTest"})
@ActiveProfiles(value = "test")
public class CatalogSearchTests {
}
