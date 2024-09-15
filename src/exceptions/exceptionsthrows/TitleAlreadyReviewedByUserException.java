package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class TitleAlreadyReviewedByUserException extends Exception{

    private final String message, user, title;

    public TitleAlreadyReviewedByUserException(String user, String title){
        message = ExceptionMessages.ErrorMessages.TITLE_ALREADY_REVIEWED_BY_USER.getMessage();
        this.user = user;
        this.title = title;
    }

    public String getMessage() { return String.format(message,user,title); }
}
