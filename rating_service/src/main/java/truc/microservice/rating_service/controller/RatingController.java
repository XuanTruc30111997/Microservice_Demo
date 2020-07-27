package truc.microservice.rating_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import truc.microservice.rating_service.exception.ResourceNotFoundException;
import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingCount;
import truc.microservice.rating_service.model.RatingInsert;
import truc.microservice.rating_service.model.RatingList;
import truc.microservice.rating_service.service.RatingCountService;
import truc.microservice.rating_service.service.RatingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @Autowired
    RatingCountService ratingCountService;

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
    }

    @GetMapping("/ratingsByMovieId/{movieId}")
    public ResponseEntity<RatingList> getRatingsByMovieId(@PathVariable("movieId") Integer movieId)
    {
        return new ResponseEntity<>(ratingService.findRatingsByMovieId(movieId), HttpStatus.OK);
    }

    @GetMapping("/avgStarByMovieId/{movieId}")
    public ResponseEntity<RatingCount> getAvgStarByMovieId(@PathVariable("movieId") Integer movieId)
    {
        return new ResponseEntity<>(ratingCountService.caculateAvgStar(movieId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Rating> insertRating(@RequestBody RatingInsert ratingInsert)
    {
        return new ResponseEntity<>(ratingService.insertRating(ratingInsert), HttpStatus.OK);
    }
}
