package user;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class CriticClass extends UserAbstract implements Critic{
    /**
     * CriticClass Constructor
     * @param name Name of the User
     */
    public CriticClass(String name) {
        super(name);
    }

    @Override
    public String getType() { return TYPE; }
}
