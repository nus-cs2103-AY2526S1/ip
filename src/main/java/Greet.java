public class Greet {
    private final String botName;

    public Greet(String name) {
        this.botName = name;
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you? ");
        System.out.println("____________________________________________________________");
    }
}
