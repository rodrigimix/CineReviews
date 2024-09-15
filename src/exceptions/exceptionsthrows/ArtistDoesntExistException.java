package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ArtistDoesntExistException extends Exception{

    private final String message, artist;

    public ArtistDoesntExistException(String artist){
        message = ExceptionMessages.ErrorMessages.ARTIST_DOESNT_EXIST.getMessage();
        this.artist = artist;
    }

    public String getMessage() { return String.format(message,artist); }
}
