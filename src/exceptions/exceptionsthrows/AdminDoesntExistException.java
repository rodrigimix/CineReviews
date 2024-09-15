package exceptions.exceptionsthrows;

import exceptions.*;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class AdminDoesntExistException extends Exception{

    private final String message, admin;

    public AdminDoesntExistException(String admin){
        message = ExceptionMessages.ErrorMessages.ADMIN_DOESNT_EXIST.getMessage();
        this.admin = admin;
    }

    public String getMessage() { return String.format(message,admin); }
}
