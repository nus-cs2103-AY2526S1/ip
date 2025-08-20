import java.util.Scanner;

public class EvansBot {

    public static void main(String[] args) {
        Greet greeter = new Greet("EvansBot");
        Exit exiter = new Exit();
        Scanner scanner = new Scanner(System.in);
        String input;

        greeter.greet();
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exiter.sayBye();
                break;
            }
            else {
                System.out.println("############################################################");
                System.out.println(input);
                System.out.println("############################################################");
            }
        }
        scanner.close();
    }
}
