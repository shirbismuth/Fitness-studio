package gym.customers;

public class Client extends Person {
    public Client(Person per) {
        super(per);
    }

    public void withdraw(int sum){
        setBalance(getBalance()-sum);
    }
}
