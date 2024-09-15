package user;

import artist.Artist;
import show.Show;

import java.util.HashMap;
import java.util.Map;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class AdminClass extends UserAbstract implements Admin{
    //Private Variables
    private String password;
    private Map<String, Show> showsCreated;
    private Map<String, Artist> artistsCreated;

    /**
     * AdminClass Constructor
     * @param name Name of the Admin
     * @param password Password of the Admin
     */
    public AdminClass(String name, String password) {
        super(name);
        this.password = password;
        this.showsCreated = new HashMap<>();
        this.artistsCreated = new HashMap<>();
    }
    @Override
    public String getPassword() { return password; }

    @Override
    public void addShowCreated(String name, Show show) { showsCreated.put(name, show); }

    @Override
    public void addArtistCreated(String name, Artist artist) { artistsCreated.put(name, artist); }

    @Override
    public int getNumberShows() { return  showsCreated.size(); }

    @Override
    public String getType() { return TYPE; }
}
