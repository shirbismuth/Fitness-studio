package gym.management.Sessions;

/**
 * The Ninja class represents a Ninja session in the gym management system.
 * It implements the Session interface and contains details about the session such as time, forum type,
 * instructor, and registered clients.
 */
public class Ninja extends Session {
    private static final int PRICE = 150;
    private static final int CAPACITY = 5;

    Ninja(String time, ForumType forumType, Instructor instructor) {
        super(time, forumType, instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.Ninja;
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