package truc.microservice.rating_service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private Integer id;
    private Byte star;
    private Integer movieId;

    public Rating()
    {

    }

    public Rating(Byte star, Integer movieId)
    {
        this.star = star;
        this.movieId = movieId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Byte getStar()
    {
        return star;
    }

    public void setStar(Byte star)
    {
        this.star = star;
    }

    public Integer getMovieId()
    {
        return movieId;
    }

    public void setMovieId(Integer movieId)
    {
        this.movieId = movieId;
    }
}
