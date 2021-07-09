package models;

/**
 * NOTE: This Movile class is already complete and is the main model for this project
 */
public class Movie implements Comparable<Movie> {
    /**
     * The title of the movie
     */
    private String title;

    /**
     * The genre of the movie
     */
    private String genre;

    /**
     * The checkout status
     */
    private boolean checkoutStatus;

    /**
     * Creates an instance of the Movie class
     * @param title The movie title
     * @param genre The artist's name
     */
    public Movie(String title, String genre, boolean checkoutStatus) {
        this.title = title;
        this.genre = genre;
        this.checkoutStatus = checkoutStatus;
    }

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    /**
     * Gets the title
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the genre
     * @return The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the checkoutStatus
     * @return The checkoutStatus
     */
    public boolean getCheckoutStatus() {
        return checkoutStatus;
    }

    /**
     * Sets the title if it is not null or whitespace
     * @param title The title
     */
    protected void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the genre if it is not null or whitespace
     * @param genre The genre
     */
    protected void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets the checkoutStatus if it is not null or whitespace
     * @param checkoutStatus The genre
     */
    protected void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    /**
     * Converters a models.Movie to a string description
     * @return The string representation of a 'models.Movie' object
     */
    @Override
    public String toString()
    {
        if(getCheckoutStatus()) {
            return "MOVIE TITLE: '" + getTitle() + "' genre: '" + getGenre() + "' " + "Status: Being rented";
        }
        else {
            return "MOVIE TITLE: '" + getTitle() + "' genre: '" + getGenre() + "' "+ "Status: Available for rent";
        }
    }

    /**
     * Determines if this models.Movie obj is equal to the provided object
     * @param obj The compared object
     * @return True if the Movie are the same, and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Checks if 'obj' is null or can't be assigned to a 'models.Movie' class
        if (obj == null || !Movie.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        // Casts the 'obj' to a 'models.Movie' object
        final Movie otherMovie = (Movie)obj;

        // Checks if the name and artist are the same
        if (this.getTitle().equals(otherMovie.getTitle()) && this.getGenre().equals(otherMovie.getGenre())) {
            return true;
        }

        // Returns false, as a catch all
        return false;
    }

    /**
     * IMPORTANT NOTE: THIS METHOD IS NEEDED FOR SIMPLICITY IN THE HOMEWORK, BUT IT IS NOT BEGINNER FRIENDLY YOU DO NOT NEED TO TOUCH THIS METHOD
     *                 For anyone who wants to learn more about compareTo read this link, but you have been warned: https://medium.com/omarelgabrys-blog/comparing-objects-307400115f88
     * @param otherMovie The other movie object
     * @return 0 if the movies are equal and other numbers if not
     */
    @Override
    public int compareTo(Movie otherMovie) {
        // Compares the models.Movie Title by sending the compareTo call to the string level to find if they are different
        if (this.getTitle().compareTo(otherMovie.getTitle()) > 0) { return 1; }
        else if (this.getTitle().compareTo(otherMovie.getTitle()) < 0) { return -1; }
        else {
            // Compares the Genre by sending the compareTo call to the string level to find if they are different
            if (this.getGenre().compareTo(otherMovie.getGenre()) > 0) { return 1; }
            else if (this.getGenre().compareTo(otherMovie.getGenre()) < 0) { return -1; }

            // If we reach here both the title and genre were 0 meaning they were equal
            else { return 0; }
        }
    }

    /**
     * The main method
     * @param args The array of arguments
     */
    public static void main(String[] args) {
        Movie Movie1 = new Movie("Reply 1988", "Comedy",false);
        Movie Movie2 = new Movie("Start Up", "Romance",false);
        Movie Movie3 = new Movie("NameOne", "ArtOne",false);

        Movie1.compareTo(Movie2);
        Movie1.compareTo(Movie3);

        // Using the 'toString' method
        System.out.println(Movie1);
        System.out.println(Movie2);

        // Using the 'equals' method2
        System.out.println("Movies are equal: " + Movie1.equals(Movie2));

        // Updating Movie2 to the same values as Movie1
        Movie2.setTitle("Reply 1988");
        Movie2.setGenre("Comedy");

        // Prints out Movie2 and checks if the Movies are now equal
        System.out.println(Movie2);
        System.out.println("Movies are equal: " + (Movie1.equals(Movie2) && Movie2.equals(Movie1)));
    }
}
