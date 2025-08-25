import java.util.Scanner;

public class ChatLoop {
    private TaskList taskList;
    private UserInputHandler userInputHandler;
    private Storage storage;

    public ChatLoop(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.userInputHandler = new UserInputHandler();
        this.storage = storage;
    }

    public void run() {
        boolean endProgram = false;
        Scanner scanner = new Scanner(System.in);

        printFormattedMessage(ZellMessage.WELCOME_MESSAGE.message());

        while (!endProgram) {
            String userInput = scanner.nextLine();
            String output = this.userInputHandler.handleInput(userInput, this.taskList, this.storage);
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
