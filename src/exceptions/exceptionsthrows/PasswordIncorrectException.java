package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class PasswordIncorrectException extends Exception{

    private final String message;

    public PasswordIncorrectException(){ message = ExceptionMessages.ErrorMessages.PASSWORD_INCORRECT.getMessage(); }

    public String getMessage() { return String.format(message); }
}
