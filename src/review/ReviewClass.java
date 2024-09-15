package review;

import user.User;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class ReviewClass implements Review{
    //Private Variables
    private final String movieTitle;
    private final User user;
    private final String comment;
    private final String overall;

    //Constants
    private static final String EXCELLENT = "EXCELLENT";
    private static final String GOOD = "GOOD";
    private static final String AVERAGE = "AVERAGE";
    private static final String POOR = "POOR";
    private static final String TERRIBLE = "TERRIBLE";


    public ReviewClass(String movieTitle, User user, String comment, String overall){
        this.movieTitle = movieTitle;
        this.user = user;
        this.comment = comment;
        this.overall = overall;
    }

    @Override
    public User getUser(){ return user; }
    @Override
    public String getComment(){ return comment; }
    @Override
    public String getOverall(){ return overall; }
    @Override
    public int getNumericOverall(){
        int rating = 0;
        switch (overall.toUpperCase()){
            case EXCELLENT -> rating = 5;
            case GOOD -> rating = 4;
            case AVERAGE -> rating = 3;
            case POOR -> rating = 2;
            case TERRIBLE -> rating = 1;
        }
        return rating;
    }



}
