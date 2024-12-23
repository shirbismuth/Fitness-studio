package gym.management.Sessions;

import gym.customers.Client;
import java.util.Set;

public interface Session {
    SessionType getSessionType();

    String getTime();

    ForumType getForumType();

    Instructor getInstructor();

    int getPrice();

    int getCapacity();

    Set<Client> getRegistered();

    void registerToLesson(Client c);
}
