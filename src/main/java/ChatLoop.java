import java.util.Scanner;

public class ChatLoop {
    private TaskList taskList;
    private UserInputHandler userInputHandler;

    public ChatLoop() {
        this.taskList = new TaskList();
        this.userInputHandler = new UserInputHandler();
    }

    public void run() {
        boolean endProgram = false;
        Scanner scanner = new Scanner(System.in);

        printFormattedMessage("Hello! My name is Zell\n How can I help you?");

        while (!endProgram) {
            String userInput = scanner.nextLine();
            String output = this.userInputHandler.handleInput(userInput, this.taskList);
            printFormattedMessage(output);

            endProgram = this.userInputHandler.getEndProgram();
        }
    }

    public void printFormattedMessage(String message) {
        String formattedMessage =
                "____________________________________________________________\n" +
                        message +
                        "\n____________________________________________________________\n\n";

        System.out.println(formattedMessage);
    }
}
