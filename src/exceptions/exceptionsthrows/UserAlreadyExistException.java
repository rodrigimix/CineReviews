package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class UserAlreadyExistException extends Exception{

    private final String message, user;

    public UserAlreadyExistException(String user){
        message = ExceptionMessages.ErrorMessages.USER_ALREADY_EXIST.getMessage();
        this.user = user;
    }

    public String getMessage() { return String.format(message,user); }
}
