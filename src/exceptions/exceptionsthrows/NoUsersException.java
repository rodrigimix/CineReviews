package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class NoUsersException extends Exception{

    private final String message;

    public NoUsersException(){ message = ExceptionMessages.ErrorMessages.NO_USERS.getMessage(); }

    public String getMessage() { return String.format(message); }
}
