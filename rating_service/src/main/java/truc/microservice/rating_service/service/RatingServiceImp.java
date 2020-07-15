package truc.microservice.rating_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truc.microservice.rating_service.exception.ResourceNotFoundException;
import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingInsert;
import truc.microservice.rating_service.repository.RatingRepository;

import java.util.Optional;

@Service
public class RatingServiceImp implements RatingService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating getRatingById(Integer id) {
        Optional<Rating> ratingOptional = ratingRepository.findById(id);
        if(ratingOptional.isEmpty())
        {
            throw new ResourceNotFoundException("Not found rating");
        }

        return ratingOptional.get();
    }

    @Override
    public Rating insertRating(RatingInsert ratingInsert) {
        Rating rating = new Rating(ratingInsert.getStar(), ratingInsert.getMovieId());
        return ratingRepository.save(rating);
    }

    @Override
    public Rating findRatingByMovieId(Integer movieId) {
        Rating rating = ratingRepository.findByMovieId(movieId);
        if (rating == null)
        {
            throw new ResourceNotFoundException("Not found rating of Product");
        }

        return rating;
    }
}
