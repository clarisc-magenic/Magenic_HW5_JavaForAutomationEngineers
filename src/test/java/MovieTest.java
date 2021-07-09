/**
 * NOTE: This Test class is already complete and should be used as reference for the other test cases
 */

import models.Movie;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieTest {
    /**
     * This test is for the 'Getters'
     */
    @Test
    public void testGetters() {
        String title = "Kissing Booth";
        String genre = "Romance";

        Movie movie = new Movie(title, genre);

        Assert.assertEquals(movie.getTitle(), title);
        Assert.assertEquals(movie.getGenre(), genre);
    }

    /**
     * This test is for the 'toString' override
     */
    @Test
    public void testToString() {
        String title = "Kissing Booth";
        String genre = "Romance";

        // This is the expected format of how the song is returned after 'toString' is called
//        String unformattedExpectedString = "SONG TITLE: '%s' by '%s'";
        String unformattedExpectedString;

        Movie movie = new Movie(title, genre);

        if(movie.getCheckoutStatus()) {
            unformattedExpectedString =  "MOVIE TITLE: '%s' genre: '%s' " + "Status: Being rented";
        }
        else {
            unformattedExpectedString = "MOVIE TITLE: '%s' genre: '%s' "+ "Status: Available for rent";
        }

        Assert.assertEquals(movie.toString(), String.format(unformattedExpectedString, title, genre));
    }

    /**
     * This test is for the 'equals' override
     */
    @Test
    public void testEquals() {
        Movie movie = new Movie("Alive", "Horror");
        Movie wrongMovie = new Movie("WRONG", "Drama");
        Movie duplicateMovie = new Movie("Alive", "Horror");

        Assert.assertFalse(movie.equals(wrongMovie));
        Assert.assertTrue(movie.equals(duplicateMovie));
    }
}