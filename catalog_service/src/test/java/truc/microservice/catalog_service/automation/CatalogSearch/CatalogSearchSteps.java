package truc.microservice.catalog_service.automation.CatalogSearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import truc.microservice.catalog_service.ResponseVerification;
import truc.microservice.catalog_service.automation.BaseCatalogServiceSteps;
import truc.microservice.catalog_service.automation.Communication;
import truc.microservice.catalog_service.model.MovieRating;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@ActiveProfiles("test")
public class CatalogSearchSteps extends BaseCatalogServiceSteps {
    private final Logger LOGGER = LoggerFactory.getLogger(CatalogSearchSteps.class);

    private LinkedMultiValueMap<String, String> parameters;

    @Before
    public void before(Scenario scenario) {
        LOGGER.info("Start Scenario: " + scenario.getName());
        String url = StringUtils.join(LOCALHOST, PORT, CATALOG_DETAILS_API);
        setClient(Communication.buildClient(url));
    }

    @After
    public void after(Scenario scenario) {
        LOGGER.info("End scenario: {}", scenario.getName());
    }

    @When("^I request with idCatalog (.*)$")
    public void setIdCatalogFilter(String idCatalog)
    {
        parameters = new LinkedMultiValueMap<>();
        parameters.add(ID_CATALOG_KEY, idCatalog);
    }

    @Then("^I expect to receive idMovie(.*), name(.*), star(.*), count(.*)$")
    public void iExpectToRecieveCorrectListMovie(String idMovie, String name, String star, String count) throws IOException {
        final WebTestClient.ResponseSpec responseSpec = Communication.sendGetRequest(getClient(), SEARCH_API, parameters);

        ResponseVerification.isOk(responseSpec);
        List<MovieRating> polResp = ResponseVerification.extracMovieRatingReponse(responseSpec);

        Assert.assertArrayEquals(toArray(idMovie, Integer[].class), getMovieId(polResp).toArray());
        Assert.assertArrayEquals(toArray(name, String[].class), getMovieName(polResp).toArray());
        Assert.assertArrayEquals(toArray(star, Double[].class), getMovieStar(polResp).toArray());
        Assert.assertArrayEquals(toArray(count, Integer[].class), getMovieCount(polResp).toArray());

    }

    @Then("^I expect to receive status code (.*)$")
    public void iExpectToReceiveCorrectStatusCode(int statusCode) {
        final WebTestClient.ResponseSpec responseSpec = Communication.sendGetRequest(getClient(), SEARCH_API, parameters);
        responseSpec.expectStatus().isEqualTo(statusCode);
    }

    private List<Integer> getMovieId(List<MovieRating> response) {
        return response.stream().map(MovieRating::getIdMovie).collect(Collectors.toList());
    }

    private List<String> getMovieName(List<MovieRating> response) {
        return response.stream().map(MovieRating::getName).collect(Collectors.toList());
    }

    private List<Double> getMovieStar(List<MovieRating> response) {
        return response.stream().map(MovieRating::getStar).collect(Collectors.toList());
    }

    private List<Integer> getMovieCount(List<MovieRating> response) {
        return response.stream().map(MovieRating::getCount).collect(Collectors.toList());
    }

    public <T> T toArray(String json, Class<T> classType) throws IOException {
        if (StringUtils.isBlank(json)) {
            return (T) Array.newInstance(classType.getComponentType(), 0);
        }
        return new ObjectMapper().readValue(json, classType);
    }

}
