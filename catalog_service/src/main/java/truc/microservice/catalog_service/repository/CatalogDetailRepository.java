package truc.microservice.catalog_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import truc.microservice.catalog_service.model.CatalogDetail;

import java.util.List;

@Repository
public interface CatalogDetailRepository extends JpaRepository<CatalogDetail, Integer> {

    @Query(value = "Select * From catalog_detail cd Where cd.id = :id", nativeQuery = true)
    List<CatalogDetail> findAllById(@Param("id") Integer id);
}
