package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class NoCollaborationBetweenArtistsException extends Exception{

    private final String message;

    public NoCollaborationBetweenArtistsException(){
        message = ExceptionMessages.ErrorMessages.NO_COLLABORATION_BETWEEN_ARTISTS.getMessage();
    }

    public String getMessage() { return String.format(message); }
}
