package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class UserDoesntExistException extends Exception{

    private final String message, user;

    public UserDoesntExistException(String user){
        message = ExceptionMessages.ErrorMessages.USER_DOESNT_EXIST.getMessage();
        this.user = user;
    }

    public String getMessage() { return String.format(message,user); }
}
