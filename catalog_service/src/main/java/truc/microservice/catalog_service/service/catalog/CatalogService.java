package truc.microservice.catalog_service.service.catalog;

import truc.microservice.catalog_service.model.Catalog;
import truc.microservice.catalog_service.model.CatalogInsert;

public interface CatalogService {
    Catalog findById(Integer id);
    Catalog insert(CatalogInsert catalogInsert);
}
