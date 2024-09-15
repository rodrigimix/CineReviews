package artist.artistComparator;

import artist.AuxiliaryClass.ArtistComparatorClass;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class SetComparator implements Comparator<SortedSet<ArtistComparatorClass>> {

    @Override
    public int compare(SortedSet<ArtistComparatorClass> o1, SortedSet<ArtistComparatorClass> o2) {
        int comparator = o2.size() - o1.size();
        if (comparator == 0) {
            Iterator<ArtistComparatorClass> it1 = o1.iterator();
            Iterator<ArtistComparatorClass> it2 = o2.iterator();
            while (it1.hasNext() && it2.hasNext() && comparator == 0)
                comparator = it1.next().compareTo(it2.next());
        }
        return comparator;
    }
}
