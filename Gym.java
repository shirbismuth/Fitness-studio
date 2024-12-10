public class Gym {
    private static Gym instance;
    private String name;
    private Secretary secratery;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecretary(Person per, int ) {
        this.secratery = secratery;
    }

    public Secretary getSecretary() {
        return secratery;
    }
}