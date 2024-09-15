/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 * It handles the information received from ShowAbstract. This class contains methos that are
 * specific to this type of Show.
 *
 */

package show;

import artist.Artist;

import java.util.*;

public class MovieClass extends ShowAbstract implements Movie{

    //Instance variables

    private final Artist director;
    private final int duration;

    private static final int MAX_ACTORS = 3;
    private static final String DIRECTOR = "DIRECTOR";
    private static final String ACTOR = "ACTOR";

    //Constructor

    /**
     * Constructor of subclass from ShowAbstract. It's defined by using same parameteres as ShowAbstract,
     * and has also the specific parameters of MovieClass which is the director and the duration.
     * It has also associated the type "MOVIE". MovieClass has an unique title.
     * @param title the name of the movie
     * @param director the director's name
     * @param duration the duration of the movie
     * @param ageCertification the age certification
     * @param releaseDate The date when was release
     */

    public MovieClass(String title, Artist director, int duration, String ageCertification, int releaseDate) {
        super(title,ageCertification,releaseDate);
        this.director = director;
        this.duration = duration;
    }

    //Methods

    @Override
    public Artist getDirector() { return  director; }

    @Override
    public int getDuration() { return duration; }

    @Override
    public String getArtistType(String name) {
        String type = null;
        if (this.director.getName().equals(name)){
            type = DIRECTOR;
        } else {
            Iterator<String> actorsIt = getActors();
            while (actorsIt.hasNext()){
                String actorTemp = actorsIt.next();
                if (actorTemp.equals(name)){
                    type = ACTOR;
                }
            }
        }
        return type;
    }

    public String toString() {

        Artist artist = getDirector();

        String output = getTitle() + "; " + artist.getName() + "; " + getDuration() + "; " + getAgeCertification()
                + "; " + getReleaseDate()+ "; " + getMainGenre();

        int counter = 0;

        Iterator<String> actorIt = getActors();

        while (actorIt.hasNext() && counter< MAX_ACTORS){
            String actor = actorIt.next();
            output += "; " + actor;
            counter++;
        }

        return output;

    }

}
