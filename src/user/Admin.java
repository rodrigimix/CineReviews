package user;

import artist.Artist;
import show.Show;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public interface Admin {
    /**
     * Indicates the type of User in this case Critic.
     */
    String TYPE = "ADMIN";

    /**
     * Returns the password created by the admin.
     * @return the password of the admin.
     */
    String getPassword();

    /**
     * Stores the shows that the admin created in HashMap showsCreated.
     * @param name title of the show
     * @param show object of the show
     */
    void addShowCreated(String name, Show show);

    /**
     * Saves the artists that the admin added to HashMap artistsCreated.
     * @param name name of the artist
     * @param artist object of the artist
     */
    void addArtistCreated(String name, Artist artist);

    /**
     * Returns the size of the showsCreated HashMap.
     * @return the size of the showsCreated HashMap
     */
    int getNumberShows();
}
