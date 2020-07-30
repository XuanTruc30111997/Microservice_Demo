package truc.microservice.movie_service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Double avgStar;

    public Movie() {

    }

    public Movie(Integer id, String name, Double avgStar) {
        this.id = id;
        this.name = name;
        this.avgStar = avgStar;
    }

    public Movie(String name, Double avgStar) {
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

    public Double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(Double avgStar) {
        this.avgStar = avgStar;
    }
}
