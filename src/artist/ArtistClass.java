package artist;

import java.util.HashSet;
import java.util.Set;
import java.util.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ArtistClass implements Artist{
    //Private Variables
    private final String name;
    private final String date;
    private final String birthPlace;
    private final Set<String> showParticipatedIn;

    /**
     * ArtistClass Construct
     * @param name Name of the Artist
     * @param date Date of Birth of the Artist
     * @param birthPlace Place of Birth
     */
    public ArtistClass(String name, String date, String birthPlace){
        this.name = name;
        this.date = date;
        this.birthPlace = birthPlace;
        this.showParticipatedIn = new HashSet<>();
    }

    @Override
    public String getName(){ return name; }
    @Override
    public String getDate(){ return date; }
    @Override
    public String getBirthPlace(){ return birthPlace; }
    @Override
    public void addShowParticipatedIn(String title){ showParticipatedIn.add(title); }
    @Override
    public int checkShowsInCommon(Artist other){
        int common = 0;
        for (String titleShow: showParticipatedIn) {
            if (other.checkEachShow(titleShow))
                common++;
        }
        return common;
    }
    @Override
    public boolean checkEachShow(String title){ return showParticipatedIn.contains(title); }

}
