package gym.customers;

import java.time.LocalDate;
import java.time.Period;

public class Person implements personInterface {
    private static int countID = 1111;

    private String name;
    private int balance;
    private Gender gender;
    private String birthday;
    private int id;

    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
        this.id = countID++;
    }

    public Person(Person per) {
        this.name = per.name;
        this.balance = per.balance;
        this.gender = per.gender;
        this.birthday = per.birthday;
        this.id = per.id;
    }

    public int getAge() {
        String[] parts = birthday.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public String getName() {
        return this.name;
    }

    public int getBalance(){
        return this.balance;
    }

    public Gender getGender(){
        return this.gender;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public int getID() {
        return this.id;
    }

    public void deposit(int sum){
        this.balance += sum;
    }

    public void withdraw(int sum){
        this.balance -= sum;
    }
}