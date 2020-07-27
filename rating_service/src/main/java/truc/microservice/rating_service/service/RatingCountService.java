package truc.microservice.rating_service.service;

import truc.microservice.rating_service.model.RatingCount;

public interface RatingCountService {
    RatingCount caculateAvgStar(Integer movieId);
}
