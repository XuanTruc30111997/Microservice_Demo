package truc.microservice.catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import truc.microservice.catalog_service.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
