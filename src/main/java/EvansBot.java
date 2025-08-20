public class EvansBot {

    public static void main(String[] args) {
        Greet greeter = new Greet("EvansBot");
        Exit exiter = new Exit();

        greeter.greet();
        exiter.sayBye();
    }
}
