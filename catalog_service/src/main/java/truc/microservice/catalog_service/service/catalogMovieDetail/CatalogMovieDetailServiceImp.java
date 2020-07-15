package truc.microservice.catalog_service.service.catalogMovieDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import truc.microservice.catalog_service.model.Catalog;
import truc.microservice.catalog_service.model.MovieRating;
import truc.microservice.catalog_service.service.catalog.CatalogService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
            MovieRating movieRating = restTemplate.getForObject("http://movie_serivce:8087/api/movie_rating/" + catalogDetail.getMovieId(), MovieRating.class);
            movieRatings.add(movieRating);
        }

        return movieRatings;
    }
}
