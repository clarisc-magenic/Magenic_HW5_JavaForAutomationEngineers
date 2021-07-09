package models;

import java.util.*;

public class MovieDatabase {
    /**
     * A unique list of every movie in the models.Movie Database
     */
    private ArrayList<Movie> movieArchive;

    /**
     * Creates an instance of the MovieDatabase class
     */
    public MovieDatabase() {
        movieArchive = new ArrayList<Movie>();
    }

    /**
     * Gets the Movie archive
     * @return The movie archive
     */
    public ArrayList<Movie> getMovieArchive() {
        return movieArchive;
    }

    /**
     * Sets the Movie archive
     * @param movieArchive The new movie archive
     */
    protected void setMovieArchive(ArrayList<Movie> movieArchive) {
        this.movieArchive = movieArchive;
    }

    /**
     * @param newMovie The movie to be added
     */
    public void addMovie(Movie newMovie) {
        // Add movie to the Movie archive
        movieArchive.add(newMovie);
    }

    /**
     * Deletes the given movie from the movie archive
     * @param movieToBeDeleted The movie to be deleted
     */
    public void deleteMovie(Movie movieToBeDeleted) {
        // First we must check if the movie is in our archive, otherwise we would be wasting time cause why delete what doesn't exist?
        if (movieArchive.contains(movieToBeDeleted)) {
            // Cycles through each movie in the archive to find the matching movie
            for (Movie movie : movieArchive) {
                // Checks if the current movie is equal to the movie that should be deleted
                if (movie.equals(movieToBeDeleted)) {
                    // Removes the movie from the archives
                    movieArchive.remove(movie);

                    // NOTE: This is a really tricky line of code but it IS needed, before reading the rest think why a 'break' ('return' works too) is needed here
                    // Explanation: This break will stop going through each movie in the movie archive. If you don't stop the loop here,
                    //              the loop will try to grab the next movie in the archive, BUT the archive as been change.
                    //              This will throw an exception, thus you should normally escape a loop, if what you are cycling through is changed.
                    break;
                }
            }
        }
    }

    /**
     * Returns a List of models.Movie object from archive, and an empty list if no movie are found
     * @param movieTitle The movie name
     * @return The List of models.Movie object, and an empty list if no movies are found
     */
    public List<Movie> findMovieByTitle(String movieTitle) {
        List<Movie> ListOfMovies = new ArrayList<Movie>();

        for (Movie s : movieArchive) {
            if (s.getTitle().equals(movieTitle)) {
                ListOfMovies.add(s);
            }
        }
        return ListOfMovies;
    }

    /**
     * Returns a List of models.Movies object from archive, and an empty list if no movies from the genre are found
     * @param genre The movie genre
     * @return The List of models.Movie object, and an empty list if no movies from the genre are found
     */
    public List<Movie> findMovieByGenre(String genre) {
        List<Movie> listOfMovies = new ArrayList<Movie>();

        for (Movie s : movieArchive) {
            if (s.getGenre().equals(genre)) {
                listOfMovies.add(s);
            }
        }
        return listOfMovies;
    }

    /**
     * The main method of the Movie Database class
     * @param args The array of arguments
     */
    public static void main(String[] args) {
        MovieDatabase db = new MovieDatabase();
        db.addMovie(new Movie("Hotel Del Luna", "Romance",false));
        db.addMovie(new Movie("Oh My Ghost", "Fantasy",false));
        db.addMovie(new Movie("Fight for my Way", "Romantic Comedy",false));
        System.out.println(db.getMovieArchive().size());

        System.out.println("Movie archive contains?: "+db.movieArchive.contains(new Movie("TEST NAME", "TEST ART",false)));
        db.deleteMovie(new Movie("TEST NAME", "TEST ART",false));
        System.out.println(db.movieArchive.contains(new Movie("Fight for my Way", "Romantic Comedy",false)));
        List<Movie> ListOfMovies = new ArrayList<Movie>();
        String movieTitle = "Hotel Del Luna";

            for (Movie s : db.movieArchive) {
                if (s.getTitle().equals(movieTitle)) {
                    ListOfMovies.add(s);
                }
            }

        System.out.println(db.getMovieArchive());
        System.out.println("List of Movies: " +ListOfMovies);
    }
}
