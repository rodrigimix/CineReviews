package review.reviewComparators;

import review.Review;
import user.Audience;
import user.Critic;

import java.util.Comparator;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ReviewComparatorByTypeAndScore implements Comparator<Review> {
    /**
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return if comparator<0 (review precedes the other), if comparator>0 (show succeeds the other), if
     *      same type is sorted by overall, otherwise sorted by alphabetic name.
     */
    @Override
    public int compare(Review o1, Review o2) {
        int comparator;
        if (o1.getUser().getType().equals(Audience.TYPE) && o2.getUser().getType().equals(Critic.TYPE)){
            comparator = 1;
        } else if (o1.getUser().getType().equals(Critic.TYPE) && o2.getUser().getType().equals(Audience.TYPE)) {
            comparator = -1;
        } else if (o1.getUser().getType().equals(Critic.TYPE) && o2.getUser().getType().equals(Critic.TYPE)){
            if (o1.getNumericOverall() > o2.getNumericOverall()){
                comparator = -1;
            } else if (o1.getNumericOverall() < o2.getNumericOverall()) {
                comparator = 1;
            } else {
                comparator = o1.getUser().getName().compareTo(o2.getUser().getName());
            }
        } else {
            if (o1.getNumericOverall() > o2.getNumericOverall()){
                comparator = -1;
            } else if (o1.getNumericOverall() < o2.getNumericOverall()) {
                comparator = 1;
            } else {
                comparator = o1.getUser().getName().compareTo(o2.getUser().getName());
            }
        }
        return comparator;
    }
}
