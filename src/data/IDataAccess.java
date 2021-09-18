package data;

import domain.Movie;
import exception.DataAccessEx;
import exception.ReaddingDataEx;
import exception.WritingDataEx;

import java.io.FileNotFoundException;
import java.util.List;

public interface IDataAccess {
    boolean isPresent(String resourceName) throws DataAccessEx, FileNotFoundException;
    List<Movie> getMovies(String resourceName) throws ReaddingDataEx;
    void newMovie(String resourceName, Movie movie, boolean append) throws WritingDataEx;
    String findMovie(String resourceName, String movieName) throws ReaddingDataEx;
    void newResource(String resourceName) throws DataAccessEx;
    void deleteResource(String resourceName) throws DataAccessEx;
}
