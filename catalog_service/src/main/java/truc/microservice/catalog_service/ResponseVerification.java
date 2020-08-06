package truc.microservice.catalog_service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import truc.microservice.catalog_service.model.MovieRating;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResponseVerification {
    private static final Logger logger = LoggerFactory.getLogger(ResponseVerification.class);

    /**
     * <p>Assert response status code is 200</p>
     *
     * @param response response
     */
    public static void isOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
    }

    /**
     * <p>Assert response status code is 400</p>
     *
     * @param response response
     */
    public static void isBabRequest(WebTestClient.ResponseSpec response) {
        response.expectStatus().isBadRequest();
    }

    public static String extractResponseToString(WebTestClient.ResponseSpec response) {
        FluxExchangeResult<String> fluxResult = response.returnResult(String.class);
        String strResponse = new String(Objects.requireNonNull(fluxResult.getResponseBodyContent()));
        logger.info("Body Response: {}", strResponse);
        return strResponse;
    }

    public static <T> T extractResponse(WebTestClient.ResponseSpec response, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(extractResponseToString(response), clazz);
    }

    public static List<MovieRating> extracMovieRatingReponse(WebTestClient.ResponseSpec response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return Arrays.asList(objectMapper.readValue(extractResponseToString(response), MovieRating[].class));
    }
}
