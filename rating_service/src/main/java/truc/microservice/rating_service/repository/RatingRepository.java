package truc.microservice.rating_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingCount;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "Select * From rating r Where r.movieId = :movieId", nativeQuery = true)
    List<Rating> findRatingsByMovieId(@Param("movieId") Integer movieId);
}
