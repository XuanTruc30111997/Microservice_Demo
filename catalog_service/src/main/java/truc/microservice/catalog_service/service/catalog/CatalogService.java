package truc.microservice.catalog_service.service.catalog;

import truc.microservice.catalog_service.model.Catalog;

public interface CatalogService {
    Catalog findById(Integer id);
}
