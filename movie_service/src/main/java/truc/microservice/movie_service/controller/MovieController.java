package truc.microservice.movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.service.movie.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    RestTemplate restTemplate;

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

    @GetMapping("/ahihi")
    public ByteArrayResource ahihi()
    {
        return restTemplate.getForObject("http://aws-service/amazonS3/test-ahihi/movie.json", ByteArrayResource.class);
    }

}
