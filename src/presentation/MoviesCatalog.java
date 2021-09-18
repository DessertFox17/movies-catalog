package presentation;

import exception.ValidationEx;
import service.IMoviesCatalog;
import service.MoviesCatalogImp;

import java.util.Scanner;

public class MoviesCatalog {
    public static void main(String[] args) throws ValidationEx {
        var sc = new Scanner(System.in);
        var cont = true;
        IMoviesCatalog catalog = new MoviesCatalogImp();

        do {
            System.out.println("Choose an option: \n"
                    + "1. Start movies catalog\n"
                    + "2. New movie\n"
                    + "3. Get all movies\n"
                    + "4. Find a movie\n"
                    + "0. Exit");
            System.out.print("-> ");
            var option = validateOption(sc);

            switch(option){
                case 1:
                    catalog.startMoviesCatalog();
                    break;
                case 2:
                    System.out.println("Type the name of the movie");
                    var name = sc.nextLine();
                    catalog.newMovie(name);
                    break;
                case 3:
                    catalog.getMovies();
                    break;
                case 4:
                    System.out.println("Type the name of the movie to search");
                    var find = sc.nextLine();
                    catalog.findMovie(find);
                    break;
                case 0:
                    cont = false;
                    break;
                default:

                    break;
            }
        } while (cont);
        System.out.println("\nThank you, come back soon ...");

    }

    public static int validateOption(Scanner sc) throws ValidationEx {
        var option = 0;
        try {
            option = Integer.parseInt(sc.nextLine());
            return option;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationEx("Something went wrong: " + e.getMessage());
        }
    }
}
