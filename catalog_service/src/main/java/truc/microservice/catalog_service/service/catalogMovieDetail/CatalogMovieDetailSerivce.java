package truc.microservice.catalog_service.service.catalogMovieDetail;

import truc.microservice.catalog_service.model.MovieRating;

import java.util.List;

public interface CatalogMovieDetailSerivce {
    List<MovieRating> getAllByIdCatalog(Integer idCatalog);
}
