package truc.microservice.movie_service.service.movie;

import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;

public interface MovieService {
    Movie findById(Integer id);
    Movie insertMovie(MovieInsert movieInsert);
}
