package exceptions.exceptionsthrows;

import exceptions.*;
/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ProjectsInCommonException extends Exception{

    private final String message;

    public ProjectsInCommonException(){
        message = ExceptionMessages.ErrorMessages.PROJECTS_IN_COMMON.getMessage();
    }

    public String getMessage() { return String.format(message); }
}
