package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class WithoutArtistsException extends Exception{

    private final String message;

    public WithoutArtistsException(){ message = ExceptionMessages.ErrorMessages.WITHOUT_ARTISTS.getMessage(); }

    public String getMessage() { return String.format(message); }
}
