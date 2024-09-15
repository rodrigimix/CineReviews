package artist;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public interface Artist {
    /**
     * Returns the name of the Artist
     * @return name of the Artist
     */
    String getName();

    /**
     * Returns the date of birth of the Artist
     * @return date of birth of the Artist
     */
    String getDate();

    /**
     * Returns the place of birth of the Artist
     * @return place of birth of the Artist
     */
    String getBirthPlace();

    /**
     * Saves the shows that the artist participate.
     * @param title title of the show
     */
    void addShowParticipatedIn(String title);

    /**
     * Verify if the artist have commun projects with others artists.
     * @param other Name of another artist
     * @return Number of shows that have common projects.
     */
    int checkShowsInCommon(Artist other);

    /**
     * Verify if the artist participated in the show
     * @param title title of the Show
     * @return if the artist participated in the show
     */
    boolean checkEachShow(String title);

}
