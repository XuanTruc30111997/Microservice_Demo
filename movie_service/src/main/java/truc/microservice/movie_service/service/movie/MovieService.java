package truc.microservice.movie_service.service.movie;

import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;

import java.util.List;

public interface MovieService {
    Movie findById(Integer id);
    Movie insertMovie(MovieInsert movieInsert);
    void insertAll(List<Movie> movies);
}
