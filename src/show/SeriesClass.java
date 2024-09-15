package show;

import artist.Artist;

import java.util.*;

public class SeriesClass extends ShowAbstract implements Series{

    Artist creator;
    int numberSeasons;
    private static final int MAX_ACTORS = 3;
    private static final String CREATOR = "CREATOR";
    private static final String ACTOR = "ACTOR";

    public SeriesClass(String title, Artist creator, int numberSeasons, String ageCertification, int releaseDate) {
        super(title,ageCertification,releaseDate);
        this.creator = creator;
        this.numberSeasons = numberSeasons;
    }

    @Override
    public Artist getCreator() { return  creator; }

    @Override
    public int getNumberSeasons() {return numberSeasons; }


    public String toString(){

        Artist artist = getCreator();

        String output = getTitle() + "; " + artist.getName() + "; " + getNumberSeasons() + "; " + getAgeCertification()
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


    @Override
    public String getArtistType(String name) {
        String type = null;
        if (this.creator.getName().equals(name)){
            type = CREATOR;
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
}
