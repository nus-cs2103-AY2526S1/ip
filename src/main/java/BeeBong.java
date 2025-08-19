import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeeBong {
    private final String newLine = "____________________________________________________________";
    private final List<Task> tasklist = new ArrayList<>();

    private void botMessage(String message) {
        System.out.println(this.newLine);
        System.out.println(message);
        System.out.println(this.newLine);
    }

    private void botErrorMessage(String errorMessage) {
        botMessage("Bong Alert! - "+errorMessage);
    }

    private void greetingMessage() {
        botMessage("Ding Dong! Guess who? It’s B. Bong!\nHow can I bong your day brighter?");
    }

    private void exitMessage() {
        botMessage("Ding ding! Time to go. See you soon!");
    }

    private void showCommands() {
        String commandList = """
                    List - lists out all tasks
                    Mark [task no.] - mark the task with the given number as completed
                    Unmark [task no.] - mark the task with the given number as incomplete
                    Delete - removes a task from the list
                    Help - shows full command list
                    Bye / Q - exit
                    Enter a new Task name or Command""";
        botMessage(commandList);
    }

    private void listTasks() {
        // If there are no Tasks to list
        if (this.tasklist.isEmpty()) {
            botMessage("Bong! I searched high and low… still nothing to show right now.");
            return;
        }
        System.out.println(this.newLine);
        System.out.println("Bing! Here’s what’s buzzing in your list, courtesy of B. Bong:");
        for (int i = 0; i < this.tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasklist.get(i));
        }
        System.out.println(this.newLine);
    }

    private void markTaskAs(String params, boolean status) {
        // Check if params were provided
        if (params == null) {
            botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.tasklist.size()) {
                botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Mark Task as Completed/Incomplete
            if (status) this.tasklist.get(taskNum).markCompleted();
            else this.tasklist.get(taskNum).markIncomplete();
            botMessage("Bing! Task #" + (taskNum + 1) + " marked as " + (status ? "complete" : "incomplete") + "!");
        } catch (NumberFormatException e) {
            botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    private void addTask(String type, String details) {
        // Check if details is empty
        if (details == null || details.isEmpty()) {
            botErrorMessage("Missing Task Details!");
            return;
        }
        // Check for valid Task type
        if (!type.equals("todo") && !type.equals("event") && !type.equals("deadline")) {
            botErrorMessage("That task type doesn’t exist. Try a real one!");
            return;
        }
        // Create new Task
        Task newTask = null;
        if (type.equals("event")) {
            try {
                String[] taskInfo = convertDetailsToEventTaskInfo(details);
                newTask = new EventTask(taskInfo[0], taskInfo[1], taskInfo[2]);
            } catch (BBongException e) {
                botErrorMessage("Invalid Task Details for Event Task!");
                return;
            }
        } else if (type.equals("deadline")) {
            try {
                String[] taskInfo = convertDetailsToDeadlineTaskInfo(details);
                newTask = new DeadlineTask(taskInfo[0], taskInfo[1]);
            } catch (BBongException e) {
                botErrorMessage("Invalid Task Details for Deadline Task!");
                return;
            }
        } else {
            newTask = new ToDoTask(details);
        }
        // Add Task to taskList
        this.tasklist.add(newTask);
        botMessage("Bing! Task added to my list:\n"+newTask+"\nYou now have "+this.tasklist.size()+" task(s) buzzing around in the list.");
    }

    private String[] convertDetailsToDeadlineTaskInfo(String details) throws BBongException {
        // e.g. "return book /by Sunday
        String[] taskInfo = details.split(" /by ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (taskInfo.length != 2) throw new BBongException("Invalid Task Details for Deadline Task");
        return taskInfo;
    }

    private String[] convertDetailsToEventTaskInfo(String details) throws BBongException {
        // e.g. "project meeting /from Mon 2pm /to 4pm"
        String[] result = new String[] {"", "", ""}; // name, from, to
        // Split string based on /from
        String[] temp = details.split(" /from ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) throw new BBongException("Invalid Task Details for Event Task");
        result[0] = temp[0];
        // Split string based on /to
        temp = temp[1].split(" /to ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) throw new BBongException("Invalid Task Details for Event Task");
        result[1] = temp[0];
        result[2] = temp[1];
        return result;
    }

    private void deleteTask(String params) {
        // Check if params were provided
        if (params == null) {
            botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.tasklist.size()) {
                botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Delete Task
            Task removedTask = this.tasklist.remove(taskNum);
            botMessage("Bing! This task has been removed:\n"+removedTask+"\nYou now have "+this.tasklist.size()+" task(s) buzzing around in the list.");
        } catch (NumberFormatException e) {
            botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    public void start() {
        greetingMessage();
        showCommands();
        Scanner s = new Scanner(System.in);

        boolean running = true;
        while (running) {
            // Ask for user input
            System.out.print(">>> ");
            System.out.flush();

            // For EOF error when doing testing
            if (!s.hasNextLine()) {
                break;
            }

            String input = s.nextLine().trim();

            // Check for Commands
            String[] commandParts = input.split(" ", 2);
            String command = commandParts[0].toLowerCase();
            String params = commandParts.length > 1 ? commandParts[1] : null;
            switch (command) {
                // Exit
                case "bye":
                case "q":
                    exitMessage();
                    running = false;
                    break;
                // Help
                case "help":
                    showCommands();
                    break;
                // List Tasks
                case "list":
                    // List all Tasks
                    listTasks();
                    break;
                // Mark Tasks
                case "mark":
                case "unmark":
                    // Mark the task as complete or incomplete
                    markTaskAs(params, command.equals("mark"));
                    break;
                // Add Tasks
                case "deadline":
                case "todo":
                case "event":
                    addTask(command, params);
                    break;
                case "delete":
                    deleteTask(params);
                    break;
                default:
                    botErrorMessage("Unknown Command! Something went boom in B. Bong’s circuits.");
            }
        }
    }
}
