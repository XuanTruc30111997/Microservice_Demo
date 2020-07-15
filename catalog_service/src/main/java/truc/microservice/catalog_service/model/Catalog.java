package truc.microservice.catalog_service.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "catalog")
    private Set<CatalogDetail> catalogDetails = new HashSet<>();

    public Catalog(){

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<CatalogDetail> getCatalogDetails()
    {
        return catalogDetails;
    }

    public void setCatalogDetails(Set<CatalogDetail> catalogDetails)
    {
        this.catalogDetails = catalogDetails;
    }

    public void addCatalogDetail(CatalogDetail catalogDetail)
    {
        this.catalogDetails.add(catalogDetail);
    }
}
