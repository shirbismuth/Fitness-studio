package gym.management.Sessions;

/**
 * The Pilates class represents a Pilates session in the gym management system.
 * It implements the Session interface and contains details about the session such as time, forum type,
 * instructor, and registered clients.
 */
public class Pilates extends Session {
    private static final int PRICE = 60;
    private static final int CAPACITY = 30;

    /**
     * Constructs a Pilates session with the specified time, forum type, and instructor.
     *
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor of the session
     */
    Pilates(String time, ForumType forumType, Instructor instructor){
        super(time, forumType, instructor);
    }

    /**
     * Gets the session type of this Pilates session.
     *
     * @return the session type of this Pilates session
     */
    @Override
    public SessionType getSessionType() {
        return SessionType.Pilates;
    }

    /**
     * Gets the price of this Pilates session.
     *
     * @return the price of this Pilates session
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Gets the capacity of this Pilates session.
     *
     * @return the capacity of this Pilates session
     */
    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Returns a string representation of this Pilates session.
     *
     * @return a string representation of this Pilates session
     */
    @Override
    public String toString() {
        return "Session Type: " + getSessionType() + super.toString()
                + " | Participants: " + getRegistered().size() + "/" + getCapacity();
    }
}