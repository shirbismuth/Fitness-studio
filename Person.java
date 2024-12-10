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

    protected Person(Person per) {
        this.name = per.name;
        this.balance = per.balance;
        this.gender = per.gender;
        this.birthday = per.birthday;
    }
}