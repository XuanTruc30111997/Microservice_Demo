package truc.microservice.rating_service.service;

import org.springframework.stereotype.Service;
import truc.microservice.rating_service.model.Rating;
import truc.microservice.rating_service.model.RatingCount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Service
public class RatingCountServiceImp implements RatingCountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RatingCount caculateAvgStar(Integer movieId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        Root<Rating> ratingRoot = query.from(Rating.class);

        query.multiselect(criteriaBuilder.avg(ratingRoot.get("star")), criteriaBuilder.count(ratingRoot.get("id")))
            .where(criteriaBuilder.equal(ratingRoot.get("movieId"), movieId));

        TypedQuery<Tuple> typedQuery  = entityManager.createQuery(query);
        Tuple result = typedQuery.getSingleResult();

        return new RatingCount(result.get(0, Double.class), result.get(1, Long.class).intValue());
    }
}
