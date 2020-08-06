package truc.microservice.catalog_service.model;

public class MovieRating {
    private Integer idMovie;
    private String name;
    private Double star;
    private int count;

    public MovieRating() {

    }

    public MovieRating(Integer idMovie, String name, Double star, int count)
    {
        this.idMovie = idMovie;
        this.name = name;
        this.star = star;
        this.count = count;
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

    public Double getStar()
    {
        return star;
    }

    public void setStar(Double star)
    {
        this.star = star;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
