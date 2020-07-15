package truc.microservice.catalog_service.service.catalogDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import truc.microservice.catalog_service.exception.ResourceNotFoundException;
import truc.microservice.catalog_service.model.CatalogDetail;

import java.util.List;

@Service
public class CatalogDetailServiceImp implements CatalogDetailSerivce {

    @Autowired
    CatalogDetailSerivce catalogDetailSerivce;

    @Override
    public List<CatalogDetail> findAllById(Integer id) {
        List<CatalogDetail> catalogDetails = catalogDetailSerivce.findAllById(id);
        if(catalogDetails.isEmpty())
        {
            throw new ResourceNotFoundException("Not found Catalog Detail by Id " + id);
        }

        return catalogDetails;
    }
}
