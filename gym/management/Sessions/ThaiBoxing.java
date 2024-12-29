package gym.management.Sessions;

/**
 * The ThaiBoxing class represents a Thai Boxing session in the gym management system.
 * It implements the Session interface and provides details about the session such as time,
 * forum type, instructor, price, capacity, and registered clients.
 */
public class ThaiBoxing extends Session {
    private static final int PRICE = 100;
    private static final int CAPACITY = 20;

    /**
     * Constructs a ThaiBoxing session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor of the session
     */
    ThaiBoxing(String time, ForumType forumType, Instructor instructor) {
        super(time, forumType, instructor);
    }

    /**
     * Gets the session type of this ThaiBoxing session.
     *
     * @return the session type of this ThaiBoxing session
     */
    @Override
    public SessionType getSessionType() {
        return SessionType.ThaiBoxing;
    }

    /**
     * Gets the price of this ThaiBoxing session.
     *
     * @return the price of this ThaiBoxing session
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Gets the capacity of this ThaiBoxing session.
     *
     * @return the capacity of this ThaiBoxing session
     */
    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Returns a string representation of this ThaiBoxing session.
     *
     * @return a string representation of this ThaiBoxing session
     */
    @Override
    public String toString() {
        return "Session Type: " + getSessionType() + super.toString()
                + " | Participants: " + getRegistered().size() + "/" + getCapacity();
    }
}