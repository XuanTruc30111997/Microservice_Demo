package truc.microservice.movie_service.service.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truc.microservice.movie_service.exception.ResourceNotFoundException;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie findById(Integer id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty())
        {
            throw new ResourceNotFoundException("Not found Movie");
        }

        return movieOptional.get();
    }

    @Override
    public Movie insertMovie(MovieInsert movieInsert) {
        if(movieInsert == null)
        {
            throw new IllegalArgumentException("Movie Insert cannot be null");
        }

        Movie movie = new Movie(movieInsert.getName());

        return movieRepository.save(movie);
    }

    @Override
    public void insertAll(List<Movie> movies) {
        if(movies == null)
        {
            throw new IllegalArgumentException("Movies cannot be null");
        }

        movieRepository.saveAll(movies);
    }
}
