package truc.microservice.movie_service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Byte avgStar;

    public Movie() {

    }

    public Movie(Integer id, String name, Byte avgStar) {
        this.id = id;
        this.name = name;
        this.avgStar = avgStar;
    }

    public Movie (String name)
    {
        this.name = name;
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

    public Byte getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(Byte avgStar) {
        this.avgStar = avgStar;
    }
}
