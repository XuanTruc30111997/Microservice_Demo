package truc.microservice.movie_service.model;

public class RatingCount {
    private Double avgStar;
    private Integer count;

    public RatingCount(Double avgStar, Integer count) {
        this.avgStar = avgStar;
        this.count = count;
    }

    public RatingCount() {
    }

    public Double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(Double avgStar) {
        this.avgStar = avgStar;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
