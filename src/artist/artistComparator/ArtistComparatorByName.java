package artist.artistComparator;

import artist.Artist;

import java.util.Comparator;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ArtistComparatorByName implements Comparator<Artist> {
    @Override
    public int compare(Artist o1, Artist o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
