package truc.microservice.rating_service.service;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truc.microservice.rating_service.exception.ResourceNotFoundException;
import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingCount;
import truc.microservice.rating_service.model.RatingInsert;
import truc.microservice.rating_service.model.RatingList;
import truc.microservice.rating_service.repository.RatingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public RatingList findRatingsByMovieId(Integer movieId) {
        RatingList ratings = new RatingList((ArrayList<Rating>) ratingRepository.findRatingsByMovieId(movieId));
        if (ratings.getRatings() == null)
        {
            throw new ResourceNotFoundException("Not found rating of Product");
        }

        return ratings;
    }
}
