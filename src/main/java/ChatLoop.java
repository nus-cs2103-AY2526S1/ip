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

        FormatMessage.print("Hello! My name is Zell\n How can I help you?");

        while (!this.isFinished) {
            String userInput = scanner.nextLine();
            handleUserInput(userInput);
        }
    }

    public void handleUserInput(String userInput) {
        int firstSpaceIndex = userInput.indexOf(" ");
        String command = firstSpaceIndex != -1 ? userInput.substring(0, firstSpaceIndex) : userInput;

        StringBuilder stringBuilder = new StringBuilder();

        switch (command) {
        case "bye": // Handle if invalid (have spaces)
            stringBuilder.append("Goodbye. Hope to see you again soon!");
            this.isFinished = true;

            break;
        case "list":
            stringBuilder.append("Currently you have added this tasks to your list:\n");
            stringBuilder.append(this.taskList.listAllTasks());

            break;
        case "mark": // Handle if invalid (no spaces)
            // Fallthrough
        case "unmark":
            int index = 0;

            // Handle exception if it is not an int
            try {
                index = Integer.parseInt(userInput.substring(firstSpaceIndex + 1));
            } catch (NumberFormatException e) {

            }

            // Handle if IndexOutOfBoundsException
            Task currentTask = this.taskList.getTask(index);
            if (command.equals("mark")) {
                this.taskList.markTaskAsDone(index);
                stringBuilder.append("Nice! I've marked this task as done:\n ");
            } else {
                this.taskList.markTaskAsNotDone(index);
                stringBuilder.append("OK, I've marked this task as not done yet:\n ");
            }

            stringBuilder.append(currentTask);

            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event": // Handle if invalid (no spaces)
            stringBuilder.append("Noted. The following task has been added:\n ");

            String userInputSecondHalf = userInput.substring(firstSpaceIndex + 1);
            Task task = null;


            if (command.equals("todo")) {
                task = new ToDo(userInputSecondHalf);
            } else if (command.equals("deadline")) {
                String splitBy = " /by ";
                int firstByIndex = userInputSecondHalf.indexOf(splitBy); // Handle exception if missing; -1

                String name = userInputSecondHalf.substring(0, firstByIndex);
                String dueBy = userInputSecondHalf.substring(firstByIndex + splitBy.length());

                task = new Deadline(name, dueBy);
            } else { // Event
                String splitFrom = " /from ";
                String splitTo = " /to ";

                int firstFromIndex = userInputSecondHalf.indexOf(splitFrom);
                int firstToIndex = userInputSecondHalf.indexOf(splitTo);

                // Handle exception if wrongly formatted
                if (firstFromIndex > firstToIndex) {

                }

                String name = userInputSecondHalf.substring(0, firstFromIndex);
                String start = userInputSecondHalf.substring(firstFromIndex + splitFrom.length(), firstToIndex);
                String end = userInputSecondHalf.substring(firstToIndex + splitTo.length());

                task = new Event(name, start, end);
            }

            this.taskList.addTask(task);

            stringBuilder.append(task);

            String numberOfTaskMessage = String.format("\nThere are currently %d task in the list.", this.taskList.numberOfTask());
            stringBuilder.append(numberOfTaskMessage);

            break;
        default:
            break;
        }

        FormatMessage.print(stringBuilder.toString());
    }
}
