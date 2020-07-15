package truc.microservice.rating_service.model;

public class RatingInsert {
    private Byte star;
    private Integer movieId;

    public Byte getStar()
    {
        return star;
    }

    public Integer getMovieId()
    {
        return movieId;
    }
}
