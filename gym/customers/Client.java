package gym.customers;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements Observer {
    private List<String> messages;

    public Client(Person per) {
        super(per);
        messages = new ArrayList<>();
    }

    public void withdraw(int sum){
        setBalance(getBalance() - sum);
    }

    @Override
    public void update(String message) {
        messages.add(message);
    }
}
