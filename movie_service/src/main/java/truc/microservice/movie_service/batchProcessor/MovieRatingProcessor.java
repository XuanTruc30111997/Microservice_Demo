package truc.microservice.movie_service.batchProcessor;

import org.springframework.batch.item.ItemProcessor;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.Rating;

public class MovieRatingProcessor implements ItemProcessor<MovieRating, MovieRating> {
    @Override
    public MovieRating process(MovieRating movieRating) throws Exception {

        return new MovieRating(movieRating.getIdMovie(), movieRating.getName(), movieRating.getStar() + 2, 44);
    }
}
