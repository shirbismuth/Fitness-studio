package gym.management.Sessions;

/**
 * The Ninja class represents a Ninja session in the gym management system.
 * It implements the Session interface and contains details about the session such as time, forum type,
 * instructor, and registered clients.
 */
public class Ninja extends Session {
    private static final int PRICE = 150;
    private static final int CAPACITY = 5;

    /**
     * Constructs a Ninja session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor of the session
     */
    Ninja(String time, ForumType forumType, Instructor instructor) {
        super(time, forumType, instructor);
    }

    /**
     * Gets the session type of this Ninja session.
     *
     * @return the session type of this Ninja session
     */
    @Override
    public SessionType getSessionType() {
        return SessionType.Ninja;
    }

    /**
     * Gets the price of this Ninja session.
     *
     * @return the price of this Ninja session
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Gets the capacity of this Ninja session.
     *
     * @return the capacity of this Ninja session
     */
    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Returns a string representation of this Ninja session.
     *
     * @return a string representation of this Ninja session
     */
    @Override
    public String toString() {
        return "Session Type: " + getSessionType() + super.toString()
                + " | Participants: " + getRegistered().size() + "/" + getCapacity();
    }
}