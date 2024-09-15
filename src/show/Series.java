package show;

import artist.Artist;

public interface Series {
    String TYPE = "SERIES";
    Artist getCreator();

    int getNumberSeasons();
}
