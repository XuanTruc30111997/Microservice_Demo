package truc.microservice.movie_service.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import truc.microservice.movie_service.model.MovieRating;

public class MovieRatingFileMapper implements FieldSetMapper<MovieRating> {
    @Override
    public MovieRating mapFieldSet(FieldSet fieldSet) throws BindException {
        MovieRating movieRating = new MovieRating();
        movieRating.setIdMovie(fieldSet.readInt("idMovie"));
        movieRating.setName(fieldSet.readString("name"));
        movieRating.setStar(fieldSet.readDouble("star"));

        return movieRating;
    }
}
