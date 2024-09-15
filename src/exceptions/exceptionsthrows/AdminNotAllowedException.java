package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class AdminNotAllowedException extends Exception{

    private final String message, admin;

    public AdminNotAllowedException(String admin){
        message = ExceptionMessages.ErrorMessages.ADMIN_NOT_ALLOWED.getMessage();
        this.admin = admin;
    }

    public String getMessage() { return String.format(message,admin); }
}
