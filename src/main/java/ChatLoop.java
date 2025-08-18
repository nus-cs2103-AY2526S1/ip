import java.util.Scanner;

public class ChatLoop {
    private TaskList taskList;
    private boolean isFinished;

    public ChatLoop() {
        this.taskList = new TaskList();
        this.isFinished = false;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        printFormattedMessage("Hello! My name is Zell\n How can I help you?");

        while (!this.isFinished) {
            String userInput = scanner.nextLine();
            handleUserInput(userInput);
        }
    }

    public void printFormattedMessage(String message) {
        String formattedMessage =
                "____________________________________________________________\n" +
                        message +
                "\n____________________________________________________________\n\n";

        System.out.println(formattedMessage);
    }

    public void handleUserInput(String userInput) {
        int firstSpaceIndex = userInput.indexOf(" ");

        String command = firstSpaceIndex != -1 ? userInput.substring(0, firstSpaceIndex) : userInput;

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
            int index = 0;

            // Handle exception if it is not an int
            try {
                index = Integer.parseInt(userInput.substring(firstSpaceIndex + 1));
            } catch (NumberFormatException e) {

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
            this.taskList.addTask(userInput);
            printFormattedMessage("added: " + userInput);
            break;
        }
    }
}
