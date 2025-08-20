public class Userinterface {
    private final String line = "____________________________________________________________";

    public void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void sayHello() {
        print(" Hello! I'm Phuc\n" +
                " What can I do for you?");
    }

    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        print(input);
    }
}
