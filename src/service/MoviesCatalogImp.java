package service;

import data.DataAccessImp;
import data.IDataAccess;
import domain.Movie;
import exception.DataAccessEx;
import exception.ReaddingDataEx;

import java.io.FileNotFoundException;

public class MoviesCatalogImp implements IMoviesCatalog {
    private final IDataAccess data;

    public MoviesCatalogImp() {
        this.data = new DataAccessImp();
    }


    @Override
    public void newMovie(String name) {
        Movie movie = new Movie(name);
        boolean append = false;
        try {
            append = data.isPresent(RESOURCE_NAME);
            data.newMovie(RESOURCE_NAME, movie, append);
        } catch (DataAccessEx | FileNotFoundException dataAccessEx) {
            System.out.println("Something went wrong");
            dataAccessEx.printStackTrace();
        }
    }

    @Override
    public void getMovies() {
        try {
            var movies = this.data.getMovies(RESOURCE_NAME);
            movies.forEach(System.out::println);
        } catch (ReaddingDataEx readdingDataEx) {
            System.out.println("Something went wrong");
            readdingDataEx.printStackTrace();
        }
    }

    @Override
    public void findMovie(String name) {
        String result = null;
        try {
            result = this.data.findMovie(RESOURCE_NAME, name);
        } catch (ReaddingDataEx readdingDataEx) {
            System.out.println("Something went wrong");
            readdingDataEx.printStackTrace();
        }
        System.out.println(result);
    }

    @Override
    public void startMoviesCatalog() {
        try {
            if (this.data.isPresent(RESOURCE_NAME)) {
                data.deleteResource(RESOURCE_NAME);
                data.newResource(RESOURCE_NAME);
            }else{
                data.newResource(RESOURCE_NAME);
            }
        } catch (DataAccessEx | FileNotFoundException dataAccessEx) {
            System.out.println("Something went wrong");
            dataAccessEx.printStackTrace();
        }
    }
}
