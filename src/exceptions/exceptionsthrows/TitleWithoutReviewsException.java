package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class TitleWithoutReviewsException extends Exception{

    private final String message, title;

    public TitleWithoutReviewsException(String title){
        message = ExceptionMessages.ErrorMessages.TITLE_WITHOUT_REVIEWS.getMessage();
        this.title = title;
    }

    public String getMessage() { return String.format(message,title); }
}
