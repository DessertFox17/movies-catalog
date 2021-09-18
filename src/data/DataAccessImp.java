package data;

import domain.Movie;
import exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        var file = new File(resourceName);
        try {
            var writer = new PrintWriter(new FileWriter(file, append));
            writer.println(movie.toString());
            writer.close();
            System.out.println("\nMovie successfully registered");
        } catch (IOException e) {
            e.printStackTrace();
            throw new WritingDataEx("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String findMovie(String resourceName, String movieName) throws ReaddingDataEx {
        var file = new File(resourceName);
        String result = null;
        try {
            var reader = new BufferedReader(new FileReader(file));
            var line = reader.readLine();
            var index = 1;
            while (line != null) {
                if (movieName != null && movieName.contains(line)) {
                    result = "Movie: " + line + "found at index: " + index;
                    break;
                }
                line = reader.readLine();
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReaddingDataEx("Something went wrong: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void newResource(String resourceName) throws DataAccessEx {
        var file = new File(resourceName);
        try {
            var newFile = new PrintWriter(new FileWriter(file));
            newFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReaddingDataEx("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public void deleteResource(String resourceName) throws DataAccessEx {
        var file = new File(resourceName);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("File was deleted successfully");
    }
}
