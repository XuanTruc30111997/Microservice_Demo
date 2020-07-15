package truc.microservice.rating_service.service;

import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingInsert;

import java.util.Optional;

public interface RatingService {
    Rating getRatingById(Integer id);
    Rating insertRating(RatingInsert ratingInsert);
    Rating findRatingByMovieId(Integer movieId);
}
