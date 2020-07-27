package truc.microservice.rating_service.model;

import java.util.ArrayList;

public class RatingList {
    private ArrayList<Rating> ratings;

    public RatingList(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
}
