package truc.microservice.movie_service.batchReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import truc.microservice.movie_service.model.MovieRating;

import java.util.Arrays;
import java.util.List;

public class RESTMovieReader implements ItemReader<MovieRating> {

    private Logger LOGGER = LoggerFactory.getLogger(RESTMovieReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;

    private int nextMovieRatingIndex;
    private List<MovieRating> movieRatings;

    public RESTMovieReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextMovieRatingIndex = 0;
    }

    @Override
    public MovieRating read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(isMovieRatingNotInitialized())
        {
            movieRatings = fetchMovieRating();
        }

        MovieRating nextMovieRating = null;
        if(nextMovieRatingIndex < movieRatings.size())
        {
            nextMovieRating = movieRatings.get(nextMovieRatingIndex);
            nextMovieRatingIndex++;
            LOGGER.info("Ahihi", nextMovieRating);
        }

        return nextMovieRating;
    }

    private boolean isMovieRatingNotInitialized()
    {
        return this.movieRatings == null;
    }

    private List<MovieRating> fetchMovieRating()
    {
        LOGGER.debug("Fetching student data from an external API by using the url: {}", apiUrl);

//        ResponseEntity<MovieRating[]> response = restTemplate.getForEntity(apiUrl, MovieRating[].class);
//        MovieRating[] movieRatingData = response.getBody();

        ResponseEntity<StreamingResponseBody>  responseEntity = restTemplate.getForEntity(apiUrl, StreamingResponseBody.class);
        StreamingResponseBody movieRatingData = responseEntity.getBody();

        LOGGER.debug("Found {} students", movieRatingData);

        return null;
    }
}
