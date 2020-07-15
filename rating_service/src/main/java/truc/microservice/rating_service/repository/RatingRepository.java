package truc.microservice.rating_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import truc.microservice.rating_service.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "Select * From rating r Where r.movieId = :movieId", nativeQuery = true)
    Rating findByMovieId(@Param("movieId") Integer movieId);
}
