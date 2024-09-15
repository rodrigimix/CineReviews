package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class FailCriteriaException extends Exception{

    private final String message;

    public FailCriteriaException(){
        message = ExceptionMessages.ErrorMessages.FAIL_CRITERIA.getMessage();
    }

    public String getMessage() { return String.format(message); }
}
