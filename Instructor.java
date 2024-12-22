import java.util.ArrayList;

public class Instructor {
    private Person person;
    private double wage;
    private ArrayList<SessionType> arraySession;

    public Instructor(Person person, double wage, ArrayList<SessionType> arr){
        this.person=person;
        this.wage=wage;
        this.arraySession = arr;
    }
}