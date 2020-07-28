package truc.microservice.movie_service.service.movieRating;

import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.RatingCount;

public interface MovieRatingService {
    MovieRating getMovieRatingByIdMovie(Integer id);
    RatingCount getMovieRatingCountByIdMovie(Integer id);
}
