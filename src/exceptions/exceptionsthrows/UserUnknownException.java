package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class UserUnknownException extends Exception{

    private final String message;

    public UserUnknownException(){
        message = ExceptionMessages.ErrorMessages.USER_UNKNOWN.getMessage();
    }

    public String getMessage() { return String.format(message); }
}
