import java.util.Scanner;

public class BeeBong {
    private static final String newLine = "____________________________________________________________";

    public static void BotMessage(String message) {
        System.out.println(newLine);
        System.out.println(message);
        System.out.println(newLine);
    }

    public static void GreetingMessage() {
        BotMessage("Ding Dong! I'm B. Bong\nHow can I help you?");
    }

    public static void ExitMessage() {
        BotMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        GreetingMessage();
        String commands = "Q or Bye to exit ";

        while (true) {
            // Ask for user input
            System.out.println(commands);
            System.out.print(">>> ");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            if (input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("q")) break;

            BotMessage(input);
        }

        ExitMessage();
    }
}
