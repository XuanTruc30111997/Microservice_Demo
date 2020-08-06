package truc.microservice.catalog_service.automation;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;

public class Communication {
    private static final int RESPONSE_TIMEOUT = 1000;

    /**
     * <p>Build a WebTestClient with special url</p>
     *
     * @param url host url
     * @return WebTestClient with responseTimeout 1000
     */
    public static WebTestClient buildClient(String url) {
        return WebTestClient.bindToServer()
                .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT))
                .baseUrl(url)
                .build();
    }

    /**
     * <p>Send post request to host</p>
     *
     * @param client      WebTestClient build
     * @param body        request body
     * @return WebTestClient.ResponseSpec
     * @see WebTestClient.ResponseSpec
     */
    public static WebTestClient.ResponseSpec sendPostRequest(WebTestClient client, Object body) {
        return client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(body))
                .exchange();
    }

    /**
     * <p>Send get request to host</p>
     *
     * @param client      WebTestClient build
     * @param parameters  request parameter
     * @return WebTestClient.ResponseSpec
     * @see WebTestClient.ResponseSpec
     */
    public static WebTestClient.ResponseSpec sendGetRequest(
            WebTestClient client,
            String uri,
            MultiValueMap<String, String> parameters) {
        return client
                .get()
                .uri(uriBuilder -> uriBuilder.path(uri).queryParams(parameters).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    /**
     * Send get request to host
     */
    public static WebTestClient.ResponseSpec sendPostRequest(
            WebTestClient client,
            String uri,
            MultiValueMap<String, String> parameters) {
        return client
                .post()
                .uri(uriBuilder -> uriBuilder.path(uri).queryParams(parameters).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }
}
