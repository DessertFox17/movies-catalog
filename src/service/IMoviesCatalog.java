package service;

public interface IMoviesCatalog {
    String RESOURCE_NAME = "movies.txt";
    void newMovie(String name);
    void getMovies();
    void findMovie(String name);
    void startMoviesCatalog();
}
