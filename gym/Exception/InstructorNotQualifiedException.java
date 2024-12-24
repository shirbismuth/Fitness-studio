package gym.Exception;

import gym.management.Secretary;

public class InstructorNotQualifiedException extends Exception{
    public InstructorNotQualifiedException() {
        super("Error: gym.management.Sessions.Instructor is not qualified to conduct this session type.");
        Secretary.docHistory(getMessage());
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}