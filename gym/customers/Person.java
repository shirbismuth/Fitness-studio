package gym.customers;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String name;
    private int balance;
    private Gender gender;
    private String birthday;

    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Person(Person per) {
        this.name = per.name;
        this.balance = per.balance;
        this.gender = per.gender;
        this.birthday = per.birthday;
    }

    public int getAge() {
        String[] parts = birthday.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public String getName() {
        return name;
    }
    public int getBalance(){
        return this.balance;
    }
    public Gender getGender(){
        return this.gender;
    }

}