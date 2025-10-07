package Skye.classes;
import Skye.errors.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Skye {
    private static final String DATA_FILE_PATH = "../data.txt";
    ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Prints a string of characters as given
     * @param line String to output
     */
    public static void printString(String line) {
        System.out.println(line);
    }

    /**
     * Adds a task to the list of tasks
     * @param task The given task
     */
    public void addTask(Task task) {
        tasks.add(task);
        printString("Got it. I've added this task:");
        printString("  " + task.toString());
        printString("Now you have " + this.tasks.size() + " tasks in the list.");
        saveFile();
    }

    /**
     * Prints the current list of tasks
     */
    public void listTask() {
        printString("Here are the list of tasks:");
        for (int i = 0; i < this.tasks.size(); i++) {
            printString((i + 1) + ". " + this.tasks.get(i));
        }
    }

    /**
     * Marks a task as complete
     */
    public void markTask(int number) {
        Task task = this.tasks.get(number - 1);
        task.setTaskComplete();
        printString("Well done! The task below has been completed:");
        printString(String.valueOf(task));
        saveFile();
    }

    /**
     * Marks a task as incomplete
     */
    public void unmarkTask(int number) {
        printString("Ok, I have marked the task below as incomplete:");
        Task task = this.tasks.get(number - 1);
        task.setTaskIncomplete();
        printString(String.valueOf(task));
        saveFile();
    }

    /**
     * Deletes a task
     * @param number The number of the task to delete
     */
    public void deleteTask(int number) {
        Task task = this.tasks.get(number - 1);
        this.tasks.remove(number - 1);
        printString("Got it. I have deleted this task:");
        printString(String.valueOf(task));
        printString("Now you have " + this.tasks.size() + " tasks in the list.");
        saveFile();
    }

    /**
     * Finds the list of tasks that contain a given string, and prints them
     * @param find A string to search for
     */
    public void findTask(String find) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.containsName(find)) {
                printString((i + 1) + ". " + task);
            }
        }
    }

    /**
     * Read the contents of saved file
     */
    private void readFile() {
        File myFile = new File(DATA_FILE_PATH);
        try (Scanner myReader = new Scanner(myFile)) {
            while (myReader.hasNextLine()) {
                String rawData = myReader.nextLine();
                String[] data = rawData.split("\\|");
                if (Objects.equals(data[0], "T")) {
                    Task task = new ToDo(data[2]);
                    if (Objects.equals(data[1], "Y")) {
                        task.setTaskComplete();
                    }
                    addTask(task);
                } else if (Objects.equals(data[0], "D")) {
                    LocalDate dateBy = LocalDate.parse(data[3]);
                    Deadline task = new Deadline(data[2], dateBy);
                    if (Objects.equals(data[1], "Y")) {
                        task.setTaskComplete();
                    }
                    addTask(task);
                } else if (Objects.equals(data[0], "E")) {
                    LocalDate dateFrom = LocalDate.parse(data[3]);
                    LocalDate dateBy = LocalDate.parse(data[4]);
                    Event task = new Event(data[2], dateFrom, dateBy);
                    if (Objects.equals(data[1], "Y")) {
                        task.setTaskComplete();
                    }
                    addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            printString("No previous data file found.");
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            printString("Data file seems to be corrupted! Not using data file.");
            tasks.clear();
        }
    }

    /**
     * Save the current task list into a file
     */
    private void saveFile() {
        try (FileWriter myWriter = new FileWriter(DATA_FILE_PATH)) {
            for (Task task : this.tasks) {
                myWriter.write(task.getTaskData()+"\n");
            }
        } catch (IOException e) {
            printString("Unable to write to file");
        }
    }

    /**
     * Exits the program
     */
    public static void exitProgram() {
        System.out.println("Goodbye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void main(String[] args) {
        Skye inst = new Skye();
        Scanner scanner = new Scanner(System.in);
        inst.readFile();
        String logo = " ____  _               \n"
                + "/ ___|| | ___   _  ___ \n"
                + "\\___ \\| |/ / | | |/ _ \\\n"
                + " ___) |   <| |_| |  __/\n"
                + "|____/|_|\\_\\\\__, |\\___|\n"
                + "            |___/      \n";
        System.out.println("Hello from\n" + logo);

        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();
                String[] userInputContent = userInput.split(" ", 2);
                if (userInput.equals("bye")) {
                    exitProgram();
                } else if (userInput.equals("list")) {
                    inst.listTask();
                } else if (userInput.startsWith("unmark")) {
                    if (userInputContent.length < 2) {
                        throw new IncompleteCommandException("unmark");
                    }
                    int number = Integer.parseInt(userInputContent[1]);
                    inst.unmarkTask(number);
                } else if (userInput.startsWith("mark")) {
                    if (userInputContent.length < 2) {
                        throw new IncompleteCommandException("mark");
                    }
                    int number = Integer.parseInt(userInputContent[1]);
                    inst.markTask(number);
                } else if (userInput.startsWith("delete")) {
                    if (userInputContent.length < 2) {
                        throw new IncompleteCommandException("delete");
                    }
                    int number = Integer.parseInt(userInputContent[1]);
                    inst.deleteTask(number);
                } else if (userInput.startsWith("todo")) {
                    if (userInputContent.length < 2) {
                        throw new MissingFieldException("todo", "description");
                    }
                    inst.addTask(new ToDo(userInputContent[1]));
                } else if (userInput.startsWith("deadline")) {
                    if (userInputContent.length < 2) {
                        throw new MissingFieldException("deadline", "description");
                    }
                    String[] content = userInputContent[1].split(" /by ");
                    if (content.length == 1) {
                        throw new MissingFieldException("deadline", "by");
                    }
                    LocalDate dateBy;
                    try {
                        dateBy = LocalDate.parse(content[1]);
                    } catch (DateTimeParseException e) {
                        throw new ErrorFieldException("deadline", "by");
                    }
                    inst.addTask(new Deadline(content[0], dateBy));
                } else if (userInput.startsWith("event")) {
                    if (userInputContent.length < 2) {
                        throw new MissingFieldException("event", "description");
                    }
                    String[] eventDetails = userInputContent[1].split(" /from ");
                    if (eventDetails.length == 1) {
                        throw new MissingFieldException("event", "from");
                    }
                    String[] time = eventDetails[1].split(" /to ");
                    if (time.length == 1) {
                        throw new MissingFieldException("event", "to");
                    }
                    LocalDate dateFrom, dateBy;
                    try {
                        dateFrom = LocalDate.parse(time[0]);
                    } catch (DateTimeParseException e) {
                        throw new ErrorFieldException("event", "from");
                    }
                    try {
                        dateBy = LocalDate.parse(time[0]);
                    } catch (DateTimeParseException e) {
                        throw new ErrorFieldException("event", "by");
                    }
                    inst.addTask(new Event(eventDetails[0], dateFrom, dateBy));
                } else if (userInput.startsWith("find")) {
                    if (userInputContent.length < 2) {
                        throw new MissingFieldException("find", "description");
                    }
                    inst.findTask(userInputContent[1]);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number!");
            } catch (SkyeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
