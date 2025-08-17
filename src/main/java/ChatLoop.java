import java.util.Scanner;

public class ChatLoop {
    private TaskList taskList;
    private String userInput;
    private boolean isFinished;

    public ChatLoop() {
        this.taskList = new TaskList();
        this.userInput = "";
        this.isFinished = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        printFormattedMessage("Hello! My name is Zell\n How can I help you?");

        while (!isFinished) {
            this.userInput = scanner.nextLine();
            handleCommand();
        }
    }

    public void printFormattedMessage(String message) {
        String formattedMessage =
                "____________________________________________________________\n" +
                        message +
                "\n____________________________________________________________\n\n";

        System.out.println(formattedMessage);
    }

    public void handleCommand() {
        String[] userInputSplit =this.userInput.split(" ");

        // Handle invalid command
        String command = userInputSplit[0];

        switch (command) {
        case "bye":
            printFormattedMessage("Goodbye. Hope to see you again soon!");
            this.isFinished = true;
            break;
        case "list":
            printFormattedMessage(this.taskList.listAllTasks());
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Handle if index is missing
            if (userInputSplit.length < 2) {

            }

            int index = 0;

            try {
                index = Integer.parseInt(userInputSplit[1]);
            } catch (NumberFormatException e) {
                // Handle exception
            }

            Task currentTask = this.taskList.getTask(index);
            if (command.equals("mark")) {
                this.taskList.markTaskAsDone(index);
                printFormattedMessage("Nice! I've marked this task as done:\n " + currentTask);
            } else {
                this.taskList.markTaskAsNotDone(index);
                printFormattedMessage("OK, I've marked this task as not done yet:\n " + currentTask);
            }

            break;
        default:
            this.taskList.addTask(this.userInput);
            printFormattedMessage("added: " + this.userInput);
            break;
        }
    }
}
