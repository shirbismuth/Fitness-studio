package gym.Exception;

import gym.management.Secretary;

public class InstructorNotQualifiedException extends Exception{
    public InstructorNotQualifiedException() {
        super("Error: Instructor is not qualified to conduct this session type.");
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}