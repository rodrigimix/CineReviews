package show.showComparators;

import show.Show;

import java.util.Comparator;

public class ShowYearComparator implements Comparator<Show> {
    /**
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return if comparator<0 (show precedes the other), if comparator>0 (show succeeds the other), otherwise
     *         sort by title
     */
    @Override
    public int compare(Show o1, Show o2) {
        int comparator;

        if (o1.getAvgReview()>o2.getAvgReview()){
            comparator = -1;
        } else if (o1.getAvgReview() < o2.getAvgReview()) {
            comparator = 1;
        } else {
            comparator  = o1.getTitle().compareTo(o2.getTitle());
        }

        return comparator;
    }
}
