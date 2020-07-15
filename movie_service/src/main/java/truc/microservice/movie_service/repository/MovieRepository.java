package truc.microservice.movie_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import truc.microservice.movie_service.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
