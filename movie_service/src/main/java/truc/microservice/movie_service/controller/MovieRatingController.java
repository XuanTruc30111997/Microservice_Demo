package truc.microservice.movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.service.movieRating.MovieRatingService;

@RestController
@RequestMapping("/api/movie_rating")
public class MovieRatingController {
    @Autowired
    MovieRatingService movieRatingService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieRating> getByMovieId(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(movieRatingService.getMovieRatingByIdMovie(id), HttpStatus.OK);
    }
}
