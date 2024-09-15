package user;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public interface User {
    /**
     * The getName function returns the username.
     * @return name of the user
     */
    String getName();

    /**
     * Saves the review made by the user.
     * @param title name of the show
     */
    void addReview(String title);

    /**
     * This function returns the size of the reviewsCreated List.
     * @return the size of the reviewsCreated List.
     */
    int numberReviewsCreated();

    /**
     * This function indicates whether the user is Critic, Audience or Admin.
     * @return the type of the user.
     */
    String getType();
}
