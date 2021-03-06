package truc.microservice.movie_service.service.movieRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.model.*;
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

        // With Eureka and discovery
//        RatingCount ratingCount = restTemplate.getForObject("http://rating-service/api/rating/avgStarByMovieId/" + id, RatingCount.class);

        // For kubernetes
//        RatingCount ratingCount = restTemplate.getForObject("http://localhost:8086/api/rating/avgStarByMovieId/" + id, RatingCount.class);
        RatingCount ratingCount = restTemplate.getForObject("http://rating-service:8086/api/rating/avgStarByMovieId/" + id, RatingCount.class);

        // Map to MovieRating
        return new MovieRating(movie.getId(), movie.getName(), ratingCount.getAvgStar(), ratingCount.getCount());
    }

    @Override
    public RatingCount getMovieRatingCountByIdMovie(Integer id) {
        // With Eureka and discovery
//        return restTemplate.getForObject("http://rating-service/api/rating/avgStarByMovieId/" + id, RatingCount.class);

        // For kubernetes
//        return restTemplate.getForObject("http://localhost:8086/api/rating/avgStarByMovieId/" + id, RatingCount.class);
        return restTemplate.getForObject("http://rating-service:8086/api/rating/avgStarByMovieId/" + id, RatingCount.class);
    }
}
