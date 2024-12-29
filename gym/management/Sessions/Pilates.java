package gym.management.Sessions;

public class Pilates extends Session {
    private static final int PRICE = 60;
    private static final int CAPACITY = 30;

    public Pilates(String time,ForumType forumType, Instructor instructor){
        super(time , forumType , instructor);
    }

    @Override
    public SessionType getSessionType() {
        return SessionType.Pilates ;
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