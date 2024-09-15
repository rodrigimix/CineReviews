package artist.artistComparator;

import artist.AuxiliaryClass.FriendComparatorClass;

import java.util.Comparator;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class SetFriendComparator implements Comparator<FriendComparatorClass> {
    @Override
    public int compare(FriendComparatorClass o1, FriendComparatorClass o2) {
        return o1.compareTo(o2);
    }
}
