import java.util.Scanner;

public class ChatLoop {
    public ChatLoop() {

    }

    public void run() {
        boolean isFinished = false;
        Scanner scanner = new Scanner(System.in);

        printFormattedMessage("Hello! My name is Zell\n How can I help you?");
        while (!isFinished) {
            String userInput = scanner.nextLine();

            switch (userInput) {
            case "bye":
                printFormattedMessage("Goodbye. Hope to see you again soon!");
                isFinished = true;
                break;
            default:
                printFormattedMessage(userInput);
                break;
            }
        }
    }

    public void printFormattedMessage(String message) {
        String formattedMessage =
                "____________________________________________________________\n " +
                        message +
                "\n____________________________________________________________\n\n";

        System.out.println(formattedMessage);
    }
}
