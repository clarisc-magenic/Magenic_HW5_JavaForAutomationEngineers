package models;

import resources.OptionStrings;
import utils.UserHelperMethods;

import java.util.List;
import java.util.Scanner;

/**
 * NOTE: This MP3Player class is already complete no changes are needed
 * This is a console application for an MP3 Player
 * TODO FOR HOMEWORK: Run this application and try many work flows, most of the short answer questions will be on this class
 */
public class MovieStore {
    /**
     * The Movie database instance
     */
    private MovieDatabase movieDatabase;

    /**
     * The scanner reference to read input from the user
     */
    private Scanner scanner;

    /**
     * Creates an instance of the MovieStore class
     */
    public MovieStore() {
        // Creates a new MovieDatabase
        this.movieDatabase = new MovieDatabase();
        // Sets the scanner to listing to System.in
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the Movie Database
     * @return The movie database
     */
    public MovieDatabase getMovieDatabase() {
        return movieDatabase;
    }

    /**
     * Sets the Movie database
     * @param movieDatabase The new movie database
     */
    protected void setMovieDatabase(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    /**
     * Displays the main menu and handles the user choice
     */
    private void mainMenu() {
        // Displays the main menu options and receives the users choice
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(OptionStrings.MAIN_MENU_OPTIONS);
        String userChoiceText = OptionStrings.MAIN_MENU_OPTIONS[userChoice];

        // Checks for each of the options and handles accordingly
        if (userChoiceText.equals("View Movies")) {
            // Navigates the user of to the 'View' menu
            viewMenu();
        } else if (userChoiceText.equals("Manage Movies")) {
            // Navigates the user of to the 'Manage Movies' menu
            manageMoviesMenu();
        } else if (userChoiceText.equals("Rent a Movie")) {
            String movieTitle = UserHelperMethods.getInputFromPrompt("What movie title would you like to rent: ");
            Movie foundMovie = findMovie(movieTitle);
            if(foundMovie!=null) {
                checkoutMovieForRent(foundMovie);
            }
            System.out.println("Returning to Main Menu...");
            mainMenu();
        } else {
            // Ends the program
            System.out.println("ENDING PROGRAM...");
            System.out.println("Thank you for using this MovieStore!");
        }
    }

    /**
     * Displays the view menu and handles the user choice
     */
    private void viewMenu() {
        // Displays the play menu options and deceivers the users choice
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(OptionStrings.VIEW_OPTIONS);
        String userChoiceText = OptionStrings.VIEW_OPTIONS[userChoice];

        // Checks for each of the options and handles accordingly
        if (userChoiceText.equals("View all Movies")) {
            // Get all movies from Movie Archives then display
            UserHelperMethods.printMovies(movieDatabase.getMovieArchive());
        } else if (userChoiceText.equals("Search Movie by Title")) {
            // Takes the user input for the movie name then display the result
            String movieTitle = UserHelperMethods.getInputFromPrompt("What movie title would you like to check:");
            Movie foundMovie = findMovie(movieTitle);
            if(foundMovie!=null) {
                System.out.println(foundMovie);
            }
        } else if (userChoiceText.equals("Search Movie by Genre")) {
            // Takes the user input for the movie genre then display t
            String movieGenre = UserHelperMethods.getInputFromPrompt("What movie genre would you like to check:");
            Movie foundMovieByGenre = findMovieByGenre(movieGenre);
            if(foundMovieByGenre!=null) {
                System.out.println(foundMovieByGenre);
            }
        }
            // Waits for the process to finish then returns to the Main Menu
            System.out.println("Returning to Main Menu...");
            mainMenu();

    }

    /**
     * Displays the manage movies menu and handles the user choice
     */
    private void manageMoviesMenu() {
        // Displays the manage movies menu options and deceivers the users choice
        int userChoice = UserHelperMethods.displayOptionsAndWaitForValidOption(OptionStrings.MANAGE_MOVIES_OPTIONS);
        String userChoiceText = OptionStrings.MANAGE_MOVIES_OPTIONS[userChoice];

        if (userChoiceText.equals("Add Movie")) {
            // Takes the user input for the Movie then adds the movie
            String movieTitle = UserHelperMethods.getInputFromPrompt("Movie Title:");
            String genre = UserHelperMethods.getInputFromPrompt("Genre of the Movie: " + movieTitle + ":");
            Movie movieToAdd = new Movie(movieTitle, genre);
            movieDatabase.addMovie(movieToAdd);
        } else if (userChoiceText.equals("Delete Movie")) {
            // Takes the user input for the movie title then deletes the movie
            String movieTitle = UserHelperMethods.getInputFromPrompt("What movie would you like to delete:");
            Movie movieToDelete = findMovie(movieTitle);
            movieDatabase.deleteMovie(movieToDelete);
            if(movieToDelete!=null) {
                System.out.println("Deleted Movie: '" + movieToDelete.getTitle()+"' genre: '"+movieToDelete.getGenre()+"'");
            }
        }
            // Waits for the process to finish then returns to the Main Menu
            System.out.println("Returning to Main Menu...");
            mainMenu();

    }

    /**
     * check the movie checkout status and set the status
     */
    private void checkoutMovieForRent(Movie movie) {
        //gets movie checkout status
        boolean checkoutStatus = movie.getCheckoutStatus();
        //check if the checkout status is false then set it to true and display details
        if(!checkoutStatus) {
            movie.setCheckoutStatus(true);
            System.out.println("Checkout for movie: "+"\"" + movie.getTitle()+"\""  + " is successful!");

            //displays details if checkout status is true
        } else {
            System.out.println("Sorry, the movie: "+"\"" + movie.getTitle()+"\"" + " is not available for rent!");
            System.out.println("Please select another movie. Thank you! ");

        }
    }

    /**
     * Finds the movie for the given title, if more than one movie is found, the user is prompted to select which movie from all found
     * @param movieTitle The Movie title
     * @return The movie object
     */
    private Movie findMovie(String movieTitle) {
        // Finds all movies that match the title in the database
        List<Movie> foundMovies = movieDatabase.findMovieByTitle(movieTitle);

        // If no movies were found display a message to the user, and return null
        if (foundMovies.isEmpty()) {
            System.out.println("Movie: " + movieTitle +" was not found, returning to main menu:");
            return null;
        }

        // If only one movie matches return that movie
        else if (foundMovies.size() == 1) {
            return foundMovies.get(0);
        }

        // If multiple movies match call the displayOptionsAndWaitForValidOption method to have the user select from the movies
        else {
            int selectedMovieIndex = UserHelperMethods.displayOptionsAndWaitForValidOption(foundMovies);
            return foundMovies.get(selectedMovieIndex);
        }
    }

    private Movie findMovieByGenre(String genre) {
        // Finds all movies that match the name in the database
        List<Movie> foundMovies = movieDatabase.findMovieByGenre(genre);

        // If no movies were found display a message to the user, and return null
        if (foundMovies.isEmpty()) {
            System.out.println("Movie/s with genre: " + genre +" was not found, returning to main menu:");
            return null;
        }

        // If only one movie matches return that movie
        else if (foundMovies.size() == 1) {
            return foundMovies.get(0);
        }

        // If multiple movies match call the displayOptionsAndWaitForValidOption method to have the user select from the movies
        else {
            int selectedMovieIndex = UserHelperMethods.displayOptionsAndWaitForValidOption(foundMovies);
            return foundMovies.get(selectedMovieIndex);
        }
    }

    /**
     * The main method and console app
     * @param args The list of arguments
     */
    public static void main(String[] args) {
        // Initializes the ms console app
        MovieStore ms = new MovieStore();


        // Originally Added Movies
        ms.movieDatabase.addMovie(new Movie("lovestruck in the City", "Drama", false));
        ms.movieDatabase.addMovie(new Movie("Kingdom", "Horror", false));
        ms.movieDatabase.addMovie(new Movie("love", "Drama", false));

        ms.findMovie("Kingdom");
        ms.mainMenu();
    }
}
