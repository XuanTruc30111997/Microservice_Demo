package truc.microservice.catalog_service.service.catalogMovieDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import truc.microservice.catalog_service.model.Catalog;
import truc.microservice.catalog_service.model.MovieRating;
import truc.microservice.catalog_service.service.catalog.CatalogService;

import java.util.*;

@Service
public class CatalogMovieDetailServiceImp implements CatalogMovieDetailSerivce {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CatalogService catalogService;

    @Override
    public List<MovieRating> getAllByIdCatalog(Integer idCatalog) {
        // get all catalog detail
        Catalog catalog = catalogService.findById(idCatalog);

        List<MovieRating> movieRatings = new ArrayList<>();

        // set MovieRatings
        for(var catalogDetail : catalog.getCatalogDetails())
        {
            // Fore Eureka and Discovery
//            MovieRating movieRating = restTemplate.getForObject("http://movie-service/api/movie_rating/" + catalogDetail.getMovieId(), MovieRating.class);

            // Fore Kubernetes
            MovieRating movieRating = restTemplate.getForObject("http://movie-service:8087/api/movie_rating/" + catalogDetail.getMovieId(), MovieRating.class);

            movieRatings.add(movieRating);
            movieRatings.sort(Comparator.comparing(MovieRating::getIdMovie));
        }

        return movieRatings;
    }
}
