package data;

import domain.Movie;
import exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessImp implements IDataAccess {
    @Override
    public boolean isPresent(String resourceName) throws DataAccessEx {
        var file = new File(resourceName);
        return file.exists();
    }

    @Override
    public List<Movie> getMovies(String resourceName) throws ReaddingDataEx {
        var file = new File(resourceName);
        List<Movie> movies = new ArrayList<>();

        try {
            var reader = new BufferedReader(new FileReader(file));
            var line = reader.readLine();

            while (line != null) {
                var movie = new Movie(line);
                movies.add(movie);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReaddingDataEx("Something went wrong: " + e.getMessage());
        }
        return movies;
    }

    @Override
    public void newMovie(String resourceName, Movie movie, boolean append) throws WritingDataEx {

    }

    @Override
    public Movie findMovie(String resourceName, String movieName) throws ReaddingDataEx {
        return null;
    }

    @Override
    public void newResource(String resourceName) throws DataAccessEx {

    }

    @Override
    public void deleteResource(String resourceName) throws DataAccessEx {

    }
}
