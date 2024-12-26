package gym.customers;

public interface personInterface {
    String getName();
    int getBalance();
    Gender getGender();
    String getBirthday();
    int getID();
    int getAge();
    void deposit(int sum);
    void withdraw(int sum);
}
