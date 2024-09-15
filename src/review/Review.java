package review;

import user.User;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public interface Review {
    /**
     * This function returns the user
     * @return user
     */
    User getUser();

    /**
     * Returns the comment made in the Review
     * @return comment made in the Review
     */
    String getComment();

    /**
     * Returns the qualification given in the Review
     * @return the score given in the Review
     */
    String getOverall();

    /**
     * Returns the number representative of the score given in the review.
     * @return the number representative of the score given in the review.
     */
    int getNumericOverall();
}
