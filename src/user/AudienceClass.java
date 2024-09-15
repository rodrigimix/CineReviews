package user;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class AudienceClass extends UserAbstract implements Audience{
    /**
     * AudienceClass Constructor
     * @param name Name of the User
     */
    public AudienceClass(String name) {
        super(name);
    }
    @Override
    public String getType() { return TYPE; }
}
