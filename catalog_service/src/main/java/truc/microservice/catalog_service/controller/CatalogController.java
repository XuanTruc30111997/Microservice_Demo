package truc.microservice.catalog_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import truc.microservice.catalog_service.model.Catalog;
import truc.microservice.catalog_service.model.CatalogInsert;
import truc.microservice.catalog_service.service.catalog.CatalogService;

@RestController
@RequestMapping("api/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @PostMapping
    public ResponseEntity<Catalog> add(@RequestBody CatalogInsert catalogInsert) {
        return new ResponseEntity<>(catalogService.insert(catalogInsert), HttpStatus.OK);
    }

}
