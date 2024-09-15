package artist.AuxiliaryClass;

import artist.Artist;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ArtistComparatorClass  implements Comparable<ArtistComparatorClass>{
    //Private Variables
    private final Artist artist;

    /**
     * ArtistComparatorClass Constructor
     * @param artist Artist Object
     */
    public ArtistComparatorClass(Artist artist){
        this.artist = artist;
    }

    /**
     * Returns the Artist Object
     * @return Artist Object
     */
    public Artist getArtist() { return  artist; }


    @Override
    public int compareTo(ArtistComparatorClass otherArtist) {
        return this.artist.getName().compareTo(otherArtist.getArtist().getName());
    }
}
