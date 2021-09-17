package domain;

import java.io.Serializable;

public class Movie implements Serializable{
    private final int movieId;
    private String name;
    private static int movieCounter;

    private Movie() {
        this.movieId = ++Movie.movieCounter;
    }

    public Movie(String name) {
        this();
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("id: ").append(movieId);
        sb.append(", name: ");
        sb.append('}');
        return sb.toString();
    }
}
