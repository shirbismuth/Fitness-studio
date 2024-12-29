package gym.management.Sessions;

/**
 * The MachinePilates class represents a Machine Pilates session in the gym management system.
 * It implements the Session interface and contains details about the session such as time, forum type,
 * instructor, and registered clients.
 */
public class MachinePilates extends Session {
    private static final int PRICE = 80;
    private static final int CAPACITY = 10;

    MachinePilates(String time,ForumType forumType, Instructor instructor){
        super(time, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.MachinePilates ;
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}