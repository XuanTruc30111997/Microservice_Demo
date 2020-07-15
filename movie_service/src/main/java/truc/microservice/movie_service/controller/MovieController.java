package truc.microservice.movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.service.movie.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getbyId(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Movie> create(@RequestBody MovieInsert movieInsert)
    {
        return new ResponseEntity<>(movieService.insertMovie(movieInsert), HttpStatus.OK);
    }

}
