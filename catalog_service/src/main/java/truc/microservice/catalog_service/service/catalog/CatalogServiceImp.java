package truc.microservice.catalog_service.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truc.microservice.catalog_service.exception.ResourceNotFoundException;
import truc.microservice.catalog_service.model.Catalog;
import truc.microservice.catalog_service.repository.CatalogRepository;

import java.util.Optional;

@Service
public class CatalogServiceImp implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    @Override
    public Catalog findById(Integer id) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(id);
        if(catalogOptional.isEmpty())
        {
            throw new ResourceNotFoundException("Not found Catalog");
        }

        return catalogOptional.get();
    }
}
