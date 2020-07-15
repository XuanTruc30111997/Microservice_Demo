package truc.microservice.movie_service.service.movieRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.Rating;
import truc.microservice.movie_service.service.movie.MovieService;

@Service
public class MovieRatingSerivceImp implements MovieRatingService {

    @Autowired
    MovieService movieService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public MovieRating getMovieRatingByIdMovie(Integer id) {
        // get Movie
        Movie movie = movieService.findById(id);

        // get Rating
        Rating rating = restTemplate.getForObject("http://localhost:8086/api/rating/byMovieId/1", Rating.class);

        // Map to MovieRating
        return new MovieRating(movie.getId(), movie.getName(), rating.getStar());
    }
}
