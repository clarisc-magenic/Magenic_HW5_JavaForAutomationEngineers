import models.Movie;
import models.MovieDatabase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MovieDatabaseTest {
    /**
     * This tests adding and deleting of movies
     */
    @Test
    public void testAddMovie() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", "2020", false);
        Movie Movie2 = new Movie("love", "romance", "2020", false);

        // Adds both movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);
        Assert.assertTrue(db.getMovieArchive().contains(Movie1));
        Assert.assertTrue(db.getMovieArchive().contains(Movie2));
    }

    @Test
    public void testDeleteMovie() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", "2020", false);
        Movie Movie2 = new Movie("love", "romance", "2020", false);

        // Adds both movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);
        Assert.assertTrue(db.getMovieArchive().contains(Movie1));
        Assert.assertTrue(db.getMovieArchive().contains(Movie2));

        db.deleteMovie(Movie1);
        db.deleteMovie(Movie2);
        Assert.assertTrue(!db.getMovieArchive().contains(Movie1));
        Assert.assertTrue(!db.getMovieArchive().contains(Movie2));
    }

    /**
     * This tests finding movies by Title
     */
    @Test
    public void testFindMoviesByTitle() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", "2020", false);
        Movie Movie2 = new Movie("love", "romance", "2020", false);

        // Adds both movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);

        // Finds all movies by title that match the first title and verify that the first title is found
        List<Movie> foundMovies = db.findMovieByTitle("happy");
        Assert.assertTrue(foundMovies.contains(Movie1));
    }

    /**
     * This tests finding movies by Genre
     */
    @Test
    public void testFindMoviesByGenre() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", "2020", false);
        Movie Movie2 = new Movie("love", "romance", "2020", false);

        // Adds both movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);

        // Finds all movies by genre that match the second movie and verify that the second movie is found
        List<Movie> foundMovies = db.findMovieByGenre("romance");
        Assert.assertTrue(foundMovies.contains(Movie2));
    }

    /**
     * This tests finding movies by Year
     */
    @Test
    public void testFindMoviesByYear() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", "2020", false);
        Movie Movie2 = new Movie("love", "romance", "2020", false);
        Movie Movie3 = new Movie("The Gift", "comedy", "2012", false);

        // Adds movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);
        db.addMovie(Movie3);

        // Finds all movies by genre that match the second movie and verify that the second movie is found
        List<Movie> foundMovies = db.findMovieByYear("2012");
        Assert.assertTrue(foundMovies.contains(Movie3));
    }
}