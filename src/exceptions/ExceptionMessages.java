package exceptions;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ExceptionMessages {
    /**
     * This Enum allows you to send messages for exceptions.
     */
    public enum ErrorMessages{
        USER_UNKNOWN("Unknown user type!"),
        USER_ALREADY_EXIST("User %s already exists!"),
        NO_USERS("No users registered."),
        ADMIN_DOESNT_EXIST("Admin %s does not exist!"),
        PASSWORD_INCORRECT("Invalid authentication!"),
        TITLE_ALREADY_EXIST("Show %s already exists!"),
        NO_SHOWS_UPLOADED("No shows have been uploaded."),
        BIO_ALREADY_AVAILABLE("Bio of %s is already available!"),
        ARTIST_DOESNT_EXIST("No information about %s!"),
        USER_DOESNT_EXIST("User %s does not exist!"),
        ADMIN_NOT_ALLOWED("Admin %s cannot review shows!"),
        TITLE_DOESNT_EXIST("Show %s does not exist!"),
        TITLE_ALREADY_REVIEWED_BY_USER("%s has already reviewed %s!"),
        TITLE_WITHOUT_REVIEWS("Show %s has no reviews."),

        FAIL_CRITERIA("No show was found within the criteria."),
        WITHOUT_ARTISTS("No artists yet!"),
        PROJECTS_IN_COMMON("It is a small world!"),
        NO_COLLABORATION_BETWEEN_ARTISTS("No collaborations yet!");

        private final String message;

        /**
         * This constructor receives an error message from exceptions
         * so that it can later send the desired output.
         * @param message error message from exceptions
         */
        ErrorMessages(String message) {this.message=message;}

        /**
         * Send the final message to the output.
         * @return the desired output.
         */
        public String getMessage() {return message;}
    }

}