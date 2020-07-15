package truc.microservice.movie_service.model;

import javax.persistence.criteria.CriteriaBuilder;

public class MovieRating {
    private Integer idMovie;
    private String name;
    private Byte star;

    public MovieRating() {

    }

    public MovieRating(Integer idMovie, String name, Byte star)
    {
        this.idMovie = idMovie;
        this.name = name;
        this.star = star;
    }

    public Integer getIdMovie()
    {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie)
    {
        this.idMovie = idMovie;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Byte getStar()
    {
        return star;
    }

    public void setStar(Byte star)
    {
        this.star = star;
    }
}
