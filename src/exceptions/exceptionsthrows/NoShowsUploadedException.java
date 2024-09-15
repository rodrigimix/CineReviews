package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class NoShowsUploadedException extends Exception{

    private final String message;

    public NoShowsUploadedException(){
        message = ExceptionMessages.ErrorMessages.NO_SHOWS_UPLOADED.getMessage();
    }

    public String getMessage() { return String.format(message); }
}
