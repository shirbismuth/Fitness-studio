package gym.Exception;

public class InstructorNotQualifiedException extends Exception{
    public InstructorNotQualifiedException() {
        super("Error: gym.management.Sessions.Instructor is not qualified to conduct this session type.");
    }
    public String getMessage()
    {
        return super.getMessage();
    }
}