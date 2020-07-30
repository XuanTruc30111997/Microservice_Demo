package truc.microservice.movie_service.model;

import javax.validation.constraints.NotBlank;

public class MovieInsert {
    @NotBlank(message = "Movie name cannot be blank")
    private String name;

    public MovieInsert() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
