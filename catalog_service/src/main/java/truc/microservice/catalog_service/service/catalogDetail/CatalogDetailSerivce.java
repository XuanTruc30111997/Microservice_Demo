package truc.microservice.catalog_service.service.catalogDetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import truc.microservice.catalog_service.model.CatalogDetail;

import java.util.List;

public interface CatalogDetailSerivce {
    List<CatalogDetail> findAllById(Integer id);
}
