package truc.microservice.movie_service.batchWriter;

import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import truc.microservice.movie_service.batchReader.RESTMovieReader;
import truc.microservice.movie_service.model.MovieRating;

import java.util.ArrayList;
import java.util.List;

public class RESTMovieWriter implements ItemWriter<MovieRating> {

    private Logger LOGGER = LoggerFactory.getLogger(RESTMovieReader.class);

    @Auto
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void write(List<? extends MovieRating> list) throws Exception {
        final String sql = "INSERT INTO movie (id, name, avgStar) VALUES (:idMovie, :name, :star)";
        list.forEach(item -> {
            LOGGER.info(item.getName());
        });
    }
}
