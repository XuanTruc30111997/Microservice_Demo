package truc.microservice.movie_service.model;

public class Rating {
    private Integer id;
    private Byte star;
    private Integer movieId;

    public Rating() {

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
