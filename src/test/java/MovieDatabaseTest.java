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
    public void testAddDeleteMovie() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", false);
        Movie Movie2 = new Movie("love", "romance", false);

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
     * This tests finding movies
     */
    @Test
    public void testFindingMovies() {
        // Creates the MovieDatabase instance and the testing variables
        MovieDatabase db = new MovieDatabase();
        Movie Movie1 = new Movie("happy", "drama", false);
        Movie Movie2 = new Movie("love", "romance", false);

        // Adds both movies to the database
        db.addMovie(Movie1);
        db.addMovie(Movie2);

        // Finds all movies by title that match the first title and verify that the first title is found
        List<Movie> foundMovies = db.findMovieByTitle("happy");
        Assert.assertTrue(foundMovies.contains(Movie1));

        // Finds all songs by artist that match the second song and verify that the second song is found
        foundMovies = db.findMovieByGenre("romance");
        Assert.assertTrue(foundMovies.contains(Movie2));
    }
}