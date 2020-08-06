package truc.microservice.movie_service.batch.automation;

import org.springframework.test.web.reactive.server.WebTestClient;

public class BaseMovieServiceSteps {
    protected static final String LOCALHOST = "http://localhost:";
    protected static final String MOVIE_API = "/api/movies";

    private WebTestClient client;

    public void setClient(WebTestClient client) {
        this.client = client;
    }

    public WebTestClient getClient() {
        return this.client;
    }
}
