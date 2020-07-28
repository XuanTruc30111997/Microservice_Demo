package truc.microservice.movie_service.batchProcessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.Rating;
import truc.microservice.movie_service.model.RatingCount;
import truc.microservice.movie_service.service.movieRating.MovieRatingService;

public class MovieRatingProcessor implements ItemProcessor<MovieRating, Movie> {

    @Autowired
    MovieRatingService movieRatingService;

    @Override
    public Movie process(MovieRating movieRating) throws Exception {

        RatingCount ratingCount = getRatingCount(movieRating.getIdMovie());
        return new Movie(movieRating.getName(), ratingCount.getAvgStar());
    }

    private RatingCount getRatingCount(Integer movieId)
    {
        RatingCount ratingCount = movieRatingService.getMovieRatingCountByIdMovie(movieId);
        if(ratingCount == null)
        {
            return new RatingCount((double) 0, 0);
        }

        return ratingCount;
    }
}
