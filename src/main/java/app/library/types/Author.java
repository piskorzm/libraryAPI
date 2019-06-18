package app.library.types;

import java.util.ArrayList;
import java.util.List;

public class Author {

    private String author;
    private List<Double> ratings;

    public Author(String name, Double rating) {
        this.author = name;
        this.ratings = new ArrayList<Double>();
        ratings.add(rating);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String name) {
        this.author = name;
    }

    public Double getAverageRating() {
        return this.ratings.stream().mapToDouble(Double::doubleValue).sum();
    }

    public void addBookRating(Double rating) {
        this.ratings.add(rating);
    }
}
