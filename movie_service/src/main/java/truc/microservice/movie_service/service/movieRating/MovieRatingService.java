package truc.microservice.movie_service.service.movieRating;

import truc.microservice.movie_service.model.MovieRating;

public interface MovieRatingService {
    MovieRating getMovieRatingByIdMovie(Integer id);
}
