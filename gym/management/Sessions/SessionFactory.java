package gym.management.Sessions;

public class SessionFactory {
    public static Session createSession(SessionType sessionType, String time,ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case ThaiBoxing:
                return new ThaiBoxing(time,forumType,instructor);
            case Pilates:
                return new Pilates(time,forumType,instructor);
            case MachinePilates:
                return new MachinePilates(time,forumType,instructor);
            case Ninja:
                return new Ninja(time,forumType,instructor);
            default:
                throw new IllegalArgumentException("Invalid session type");
        }
    }
}