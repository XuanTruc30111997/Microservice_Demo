package truc.microservice.catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import truc.microservice.catalog_service.model.MovieRating;
import truc.microservice.catalog_service.service.catalogDetail.CatalogDetailSerivce;
import truc.microservice.catalog_service.service.catalogMovieDetail.CatalogMovieDetailSerivce;

import java.util.List;

@RestController
@RequestMapping("/api/catalog_details")
public class CatalogMovieDetailController {
    @Autowired
    CatalogMovieDetailSerivce catalogMovieDetailSerivce;

    @GetMapping()
    public ResponseEntity<List<MovieRating>> getByIdCatalog(@RequestParam("idCatalog") Integer idCatalog)
    {
        return new ResponseEntity<>(catalogMovieDetailSerivce.getAllByIdCatalog(idCatalog), HttpStatus.OK);
    }
}
