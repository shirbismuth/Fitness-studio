package gym.management.Sessions;

/**
 * The SessionFactory class is responsible for creating instances of different session types.
 */
public class SessionFactory {

    /**
     * Creates a session based on the specified session type, time, forum type, and instructor.
     *
     * @param sessionType the type of session to create
     * @param time the time of the session
     * @param forumType the forum type of the session
     * @param instructor the instructor conducting the session
     * @return a new session instance of the specified type
     * @throws IllegalArgumentException if the session type is invalid
     */
    static Session createSession(SessionType sessionType, String time, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case ThaiBoxing:
                return new ThaiBoxing(time, forumType, instructor);
            case Pilates:
                return new Pilates(time, forumType, instructor);
            case MachinePilates:
                return new MachinePilates(time, forumType, instructor);
            case Ninja:
                return new Ninja(time, forumType, instructor);
            default:
                throw new IllegalArgumentException("Invalid session type");
        }
    }
}