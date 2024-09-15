package artist.AuxiliaryClass;

import artist.Artist;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class FriendComparatorClass implements Comparable<FriendComparatorClass> {
    //Privare Variables
    private final Artist firstArtist;
    private final Artist secondArtist;
    private final int nrColaborations;

    /**
     * FriendComparatorClass Constructor
     * @param firstArtist Artist's Object
     * @param secondArtist Artist's Object
     * @param nrColaborations Number of shows that have common projects
     */
    public FriendComparatorClass(Artist firstArtist, Artist secondArtist, int nrColaborations){
        this.firstArtist = firstArtist;
        this.secondArtist = secondArtist;
        this.nrColaborations = nrColaborations;
    }

    /**
     * Returns First Artist's Object
     * @return Artist's Object
     */
    public Artist getFirstArtist() { return firstArtist; }

    /**
     * Returns Second Artist's Object
     * @return Artist's Object
     */
    public Artist getSecondArtist() { return secondArtist; }

    /**
     * Return the number of shows that have common projects
     * @return Number of shows that have common projects
     */
    public int getNrColaborations() { return nrColaborations; }

    @Override
    public int compareTo(FriendComparatorClass otherArtist) {
        int comparator;
        if (this.nrColaborations > otherArtist.getNrColaborations()){
            comparator = -1;
        } else if (this.nrColaborations < otherArtist.getNrColaborations()) {
            comparator = 1;
        } else {
            comparator = this.firstArtist.getName().compareTo(otherArtist.firstArtist.getName());
            if (comparator == 0)
                comparator = this.secondArtist.getName().compareTo(otherArtist.secondArtist.getName());
        }
        return comparator;
    }
}
