package truc.microservice.catalog_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "catalog_detail")
@JsonIgnoreProperties("catalog_id")
public class CatalogDetail {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer movieId;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    public CatalogDetail() {

    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getMovieId()
    {
        return movieId;
    }

    public void setMovieId(Integer movieId)
    {
        this.movieId = movieId;
    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }
}
