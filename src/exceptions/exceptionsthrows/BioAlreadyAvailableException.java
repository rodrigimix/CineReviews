package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class BioAlreadyAvailableException extends Exception{

    private final String message, artist;

    public BioAlreadyAvailableException(String artist){
        message = ExceptionMessages.ErrorMessages.BIO_ALREADY_AVAILABLE.getMessage();
        this.artist = artist;
    }

    public String getMessage() { return String.format(message,artist); }
}
