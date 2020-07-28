package truc.microservice.movie_service.batchWriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.repository.MovieRepository;
import truc.microservice.movie_service.service.movie.MovieService;

import java.util.List;

public class MovieWriter implements ItemWriter<Movie> {

    @Autowired
    MovieService movieService;

    @Override
    public void write(List<? extends Movie> list) throws Exception {
        movieService.insertAll((List<Movie>) list);
    }
}
