package user;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public abstract class UserAbstract implements User{
    //Private Variables
    private String name;
    private final List<String> reviewsCreated;

    /**
     * UserAbstract Constructor.
     * @param name Name of the User
     */
    protected UserAbstract(String name){
        this.name = name;
        this.reviewsCreated = new ArrayList<>();
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void addReview(String title) { reviewsCreated.add(title); };
    @Override
    public int numberReviewsCreated() { return reviewsCreated.size();}
    public abstract String getType();
}
