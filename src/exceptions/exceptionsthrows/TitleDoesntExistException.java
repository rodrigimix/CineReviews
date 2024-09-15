package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class TitleDoesntExistException extends Exception{

    private final String message, title;

    public TitleDoesntExistException(String title){
        message = ExceptionMessages.ErrorMessages.TITLE_DOESNT_EXIST.getMessage();
        this.title = title;
    }

    public String getMessage() { return String.format(message,title); }
}
