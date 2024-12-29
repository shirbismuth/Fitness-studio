package gym.management.Sessions;

/**
 * The MachinePilates class represents a Machine Pilates session in the gym management system.
 * It implements the Session interface and contains details about the session such as time, forum type,
 * instructor, and registered clients.
 */
public class MachinePilates extends Session {
    private static final int PRICE = 80;
    private static final int CAPACITY = 10;

    /**
     * Constructs a MachinePilates session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor of the session
     */
    MachinePilates(String time, ForumType forumType, Instructor instructor){
        super(time, forumType, instructor);
    }

    /**
     * Gets the session type of this MachinePilates session.
     *
     * @return the session type of this MachinePilates session
     */
    @Override
    public SessionType getSessionType() {
        return SessionType.MachinePilates;
    }

    /**
     * Gets the price of this MachinePilates session.
     *
     * @return the price of this MachinePilates session
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Gets the capacity of this MachinePilates session.
     *
     * @return the capacity of this MachinePilates session
     */
    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Returns a string representation of this MachinePilates session.
     *
     * @return a string representation of this MachinePilates session
     */
    @Override
    public String toString() {
        return "Session Type: " + getSessionType() + super.toString()
                + " | Participants: " + getRegistered().size() + "/" + getCapacity();
    }
}