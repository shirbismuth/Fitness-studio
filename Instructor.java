import java.util.ArrayList;

public class Instructor extends Person {
    private double wage;
    private ArrayList<SessionType> arraySession;

    public Instructor(Person person, double wage, ArrayList<SessionType> arr){
        super(person);
        this.wage=wage;
        this.arraySession = arr;
    }
    public double getWage(){
        return wage;
    }

}