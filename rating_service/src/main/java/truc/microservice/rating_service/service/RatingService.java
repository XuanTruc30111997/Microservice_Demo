package truc.microservice.rating_service.service;

import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingCount;
import truc.microservice.rating_service.model.RatingInsert;
import truc.microservice.rating_service.model.RatingList;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    Rating getRatingById(Integer id);
    Rating insertRating(RatingInsert ratingInsert);
    RatingList findRatingsByMovieId(Integer movieId);
}
