import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class BeeBong {
    private final String NEWLINE = "____________________________________________________________";
    private final TaskList taskList = new TaskList();

    private void botMessage(String message) {
        System.out.println(this.NEWLINE);
        System.out.println(message);
        System.out.println(this.NEWLINE);
    }

    private void botErrorMessage(String errorMessage) {
        botMessage("Bong Alert! - " + errorMessage);
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
                    Bye - exit
                    (Enter Dates using this format: DD/MM/YYYY hh:mm, time is optional)
                    Enter a new Task name or Command""";
        botMessage(commandList);
    }

    private void listTasks() {
        // If there are no Tasks to list
        if (this.taskList.length() == 0) {
            botMessage("Bong! I searched high and low… still nothing to show right now.");
            return;
        }
        System.out.println(this.NEWLINE);
        System.out.println("Bing! Here’s what’s buzzing in your list, courtesy of B. Bong:");
        System.out.print(this.taskList);
        System.out.println(this.NEWLINE);
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
            if (taskNum < 0 || taskNum >= this.taskList.length()) {
                botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Mark Task as Completed/Incomplete
            this.taskList.markTaskAs(taskNum, status);
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
                LocalDateTime startDate = Task.parseDateTime(taskInfo[1]);
                LocalDateTime endDate = Task.parseDateTime(taskInfo[2]);
                // Make sure startDate <= endDate
                if (startDate.isAfter(endDate)) {
                    botErrorMessage("Invalid Dates Provided! Start Date cannot be after End Date!");
                    return;
                }
                newTask = new EventTask(taskInfo[0], startDate, endDate);
            } catch (InvalidTaskDetailsException e) {
                botErrorMessage("Invalid Task Details for Event Task!");
                return;
            } catch (DateTimeParseException e) {
                botErrorMessage("Invalid Dates Provided!");
                return;
            }
        } else if (type.equals("deadline")) {
            try {
                String[] taskInfo = convertDetailsToDeadlineTaskInfo(details);
                LocalDateTime deadline = Task.parseDateTime(taskInfo[1]);
                newTask = new DeadlineTask(taskInfo[0], deadline);
            } catch (InvalidTaskDetailsException e) {
                botErrorMessage("Invalid Task Details for Deadline Task!");
                return;
            } catch (DateTimeParseException e) {
                botErrorMessage("Invalid Date Provided!");
                return;
            }
        } else {
            newTask = new ToDoTask(details);
        }
        // Add Task to taskList
        this.taskList.addTask(newTask);
        botMessage("Bing! Task added to my list:\n" + newTask + "\nYou now have " + this.taskList.length() + " task(s) " +
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
            botErrorMessage("You forgot the task number!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(params) - 1;
            //Check for valid task number
            if (taskNum < 0 || taskNum >= this.taskList.length()) {
                botErrorMessage("That task number doesn’t exist. Try a real one!");
                return;
            }
            //Delete Task
            Task removedTask = this.taskList.deleteTask(taskNum);
            botMessage("Bing! This task has been removed:\n"+removedTask+"\nYou now have "+this.taskList.length()+" task(s) buzzing around in the list.");
        } catch (NumberFormatException e) {
            botErrorMessage("That task number doesn’t exist. Try a real one!");
        }
    }

    // Referenced from: https://www.w3schools.com/java/java_files_create.asp
    // and https://www.w3schools.com/java/java_files_read.asp
    private void readTasksFromFile() {
        //Check if File Exists
        File saveFile = new File("bbongSave.txt");
        // If File does not exist, create it
        if (!saveFile.exists()) {
            // Do nothing
            return;
        }
        //Else Read the saved Tasks from the file
        botMessage("Bing! Saved Tasks found, loading saved tasks...");
        try {
            Scanner reader = new Scanner(saveFile);
            while (reader.hasNextLine()) {
                String taskStr = reader.nextLine();
                taskList.addTask(Task.deserializeTask(taskStr));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            botErrorMessage("Unable to read tasks from file!");
        } catch (InvalidSerializedTaskDataException e) {
            botErrorMessage("Unable to read all saved task data, data is corrupted!");
        }
    }

    // Referenced from: https://www.w3schools.com/java/java_files_create.asp
    // and https://www.w3schools.com/java/java_files_read.asp
    private void writeTasksToFile() {
        // No need to check if the File exists before writing
        // as FileWriter automatically handles that for us.

        // Write Task List to File
        try {
            // No need to append, just write new as
            // we always read existing data and append within the tasklist
            FileWriter writer = new FileWriter("bbongSave.txt");
//            for (Task t : tasks) {
//                writer.write(t.serializeTask() + System.lineSeparator());
//            }
            writer.close();
            botMessage("Bing Bing! Tasks saved successfully!");
        } catch (IOException e) {
            botErrorMessage("Unable to save tasks to file.");
        }
    }

    public void start() {
        greetingMessage();
        showCommands();
        Scanner s = new Scanner(System.in);

        // Check for Saved Data
        readTasksFromFile();

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
            Command command = Command.NONE;
            // Convert command into Command enum
            try {
                command = Command.stringToCommand(commandParts[0].toLowerCase());
            } catch (UnknownCommandException e) {
                botErrorMessage(e.getMessage());
                continue;
            }
            String params = commandParts.length > 1 ? commandParts[1] : null;

            switch (command) {
            // Exit
            case BYE:
                writeTasksToFile();
                exitMessage();
                running = false;
                break;
            // Help
            case HELP:
                showCommands();
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
                markTaskAs(params, command == Command.MARK);
                break;
            // Add Tasks
            case DEADLINE:
            case TODO:
            case EVENT:
                addTask(command.getCommandWord(), params);
                break;
            // Delete Tasks
            case DELETE:
                deleteTask(params);
                break;
            // Unknown Commands
            default:
                botErrorMessage("Something went boom in B. Bong’s circuits.");
            }
        }
    }
}
