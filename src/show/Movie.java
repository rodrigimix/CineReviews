package show;

import artist.Artist;

public interface Movie {

    String TYPE = "MOVIE";
    Artist getDirector();

    int getDuration();

}
