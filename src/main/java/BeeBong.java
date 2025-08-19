import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeeBong {
    private static final String newLine = "____________________________________________________________";
    private static final String errorMsg = "Bong! Something went boom in B. Bong’s circuits.";
    private static int currCommand = 0;

    public static void BotMessage(String message) {
        System.out.println(newLine);
        System.out.println(message);
        System.out.println(newLine);
    }

    public static void BotMessageList(String[] messages) {
        System.out.println(newLine);
        for (int i = 0; i < currCommand; i++) {
            System.out.println((i + 1) + ". " + messages[i]);
        }
        System.out.println(newLine);
    }

    public static void GreetingMessage() {
        BotMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    public static void ExitMessage() {
        BotMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        GreetingMessage();
        String commands = "List - lists out previous inputs\nBye / Q - exit ";
        String[] userInputs = new String[100];

        boolean running = true;
        while (running) {
            // Ask for user input
            System.out.println(commands);
            System.out.print(">>> ");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            // Check for Commands
            switch (input.toLowerCase()) {
                // Bye / Q - Exit
                case "bye":
                case "q":
                    running = false;
                    break;
                // List - List all previous user inputs
                case "list":
                    if (currCommand == 0) {
                        BotMessage("Oops! I searched high and low… still nothing to show right now.");
                        break;
                    }
                    BotMessageList(userInputs);
                    break;
                default:
                    userInputs[currCommand] = input;
                    BotMessage("Added: "+input);
                    currCommand += 1;
            }
        }

        ExitMessage();
    }
}
