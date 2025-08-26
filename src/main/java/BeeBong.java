import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BeeBong {
    private TaskList taskList;
    private final Storage storage;
    private final UI ui;

    public BeeBong() {
        this.ui = new UI();
        this.storage = new Storage("bbongSave.txt");
        // Check for Saved Data
        try {
            this.taskList = new TaskList(this.storage.readTasksFromFile());
            ui.botMessage("Bing! Saved Tasks found, loading saved tasks...");
        } catch (InvalidSerializedTaskDataException e) {
            ui.botErrorMessage(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    private void listTasks() {
        // If there are no Tasks to list
        if (this.taskList.length() == 0) {
            ui.botMessage("Bong! I searched high and low… still nothing to show right now.");
            return;
        }
        ui.printBorder();
        System.out.println("Bing! Here’s what’s buzzing in your list, courtesy of B. Bong:");
        System.out.print(this.taskList);
        ui.printBorder();
    }

    private void markTaskAs(String params, boolean status) {
        // Check if params were provided
        if (params == null) {
            ui.botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.taskList.length()) {
                ui.botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Mark Task as Completed/Incomplete
            this.taskList.markTaskAs(taskNum, status);
            ui.botMessage("Bing! Task #" + (taskNum + 1) + " marked as " + (status ? "complete" : "incomplete") + "!");
        } catch (NumberFormatException e) {
            ui.botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    private void addTask(String type, String details) {
        // Check if details is empty
        if (details == null || details.isEmpty()) {
            ui.botErrorMessage("Missing Task Details!");
            return;
        }
        // Check for valid Task type
        if (!type.equals("todo") && !type.equals("event") && !type.equals("deadline")) {
            ui.botErrorMessage("That task type doesn’t exist. Try a real one!");
            return;
        }
        // Create new Task
        Task newTask = null;
        if (type.equals("event")) {
            try {
                String[] taskInfo = convertDetailsToEventTaskInfo(details);
                LocalDateTime startDate = Task.parseDateTime(taskInfo[1]);
                LocalDateTime endDate = Task.parseDateTime(taskInfo[2]);
                // Make sure startDate <= endDate
                if (startDate.isAfter(endDate)) {
                    ui.botErrorMessage("Invalid Dates Provided! Start Date cannot be after End Date!");
                    return;
                }
                newTask = new EventTask(taskInfo[0], startDate, endDate);
            } catch (InvalidTaskDetailsException e) {
                ui.botErrorMessage("Invalid Task Details for Event Task!");
                return;
            } catch (DateTimeParseException e) {
                ui.botErrorMessage("Invalid Dates Provided!");
                return;
            }
        } else if (type.equals("deadline")) {
            try {
                String[] taskInfo = convertDetailsToDeadlineTaskInfo(details);
                LocalDateTime deadline = Task.parseDateTime(taskInfo[1]);
                newTask = new DeadlineTask(taskInfo[0], deadline);
            } catch (InvalidTaskDetailsException e) {
                ui.botErrorMessage("Invalid Task Details for Deadline Task!");
                return;
            } catch (DateTimeParseException e) {
                ui.botErrorMessage("Invalid Date Provided!");
                return;
            }
        } else {
            newTask = new ToDoTask(details);
        }
        // Add Task to taskList
        this.taskList.addTask(newTask);
        ui.botMessage("Bing! Task added to my list:\n" + newTask + "\nYou now have " + this.taskList.length() + " task(s) " +
                "buzzing around in the list.");
    }

    private String[] convertDetailsToDeadlineTaskInfo(String details) throws InvalidTaskDetailsException {
        // e.g. "return book /by Sunday
        String[] taskInfo = details.split(" /by ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (taskInfo.length != 2) {
            throw new InvalidTaskDetailsException();
        }
        return taskInfo;
    }

    private String[] convertDetailsToEventTaskInfo(String details) throws InvalidTaskDetailsException {
        // e.g. "project meeting /from Mon 2pm /to 4pm"
        String[] result = new String[] {"", "", ""}; // name, from, to
        // Split string based on /from
        String[] temp = details.split(" /from ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) {
            throw new InvalidTaskDetailsException();
        }
        result[0] = temp[0];
        // Split string based on /to
        temp = temp[1].split(" /to ");
        // If after the split we have more than 2 elements, means the input is invalid
        if (temp.length != 2) {
            throw new InvalidTaskDetailsException();
        }
        result[1] = temp[0];
        result[2] = temp[1];
        return result;
    }

    private void deleteTask(String params) {
        // Check if params were provided
        if (params == null) {
            ui.botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.taskList.length()) {
                ui.botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Delete Task
            Task removedTask = this.taskList.deleteTask(taskNum);
            ui.botMessage("Bing! This task has been removed:\n"+removedTask+"\nYou now have "+this.taskList.length()+" task(s) buzzing around in the list.");
        } catch (NumberFormatException e) {
            ui.botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    public void start() {
        ui.greetingMessage();
        ui.showCommands();
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

            // Process user Input
            String input = s.nextLine().trim();

            // Check for Commands
            String[] commandParts = input.split(" ", 2);
            CommandKeyword command = CommandKeyword.NONE;
            // Convert command into Command enum
            try {
                command = CommandKeyword.stringToCommand(commandParts[0].toLowerCase());
            } catch (UnknownCommandException e) {
                ui.botErrorMessage(e.getMessage());
                continue;
            }
            String params = commandParts.length > 1 ? commandParts[1] : null;

            switch (command) {
            // Exit
            case BYE:
                // Try to save tasks to file before exiting
                try {
                    this.taskList.writeTasksToFile(this.storage);
                    ui.botMessage("Bing Bing! Tasks saved successfully!");
                } catch (BBongException e) {
                    ui.botErrorMessage(e.getMessage());
                }
                ui.exitMessage();
                running = false;
                break;
            // Help
            case HELP:
                ui.showCommands();
                break;
            // List Tasks
            case LIST:
                // List all Tasks
                listTasks();
                break;
            // Mark Tasks
            case MARK:
            case UNMARK:
                // Mark the task as complete or incomplete
                markTaskAs(params, command == CommandKeyword.MARK);
                break;
            // Add Tasks
            case DEADLINE:
            case TODO:
            case EVENT:
                addTask(command.getKeyword(), params);
                break;
            // Delete Tasks
            case DELETE:
                deleteTask(params);
                break;
            // Unknown Commands
            default:
                ui.botErrorMessage("Something went boom in B. Bong’s circuits.");
            }
        }
    }

    public static void main(String[] args) {
        new BeeBong().start();
    }
}
