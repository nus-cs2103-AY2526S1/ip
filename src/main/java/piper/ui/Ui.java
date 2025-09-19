package piper.ui;

import java.util.Scanner;

import piper.task.Task;
import piper.task.TaskList;

/**
 * Handles user interaction via the console.
 * Provides methods to read user input and print messages.
 */
public class Ui {

    // ASCII art

    /** ASCII art used in the greeting. */
    private static final String ASCII_GREET =
            " ______\n"
                    + "|_   __ \\ ( ) .\\\n"
                    + "  | |__) |__  | ''\\   .---.  _ .--.\n"
                    + "  |  ___/[  | | /'\\\\ / /__\\\\[  /''\\|\n"
                    + " _| |_    | | | \\_/ || \\__.'| |\n"
                    + "|_____|  [___]| |\\_/  \\___/[___]\n"
                    + "           / /| | \\\n"
                    + "           [ \\] |  ]\n"
                    + "            \\__ __/      (l_\n"
                    + "              | |        <'  }\n"
                    + "            [___/        (  (_\\.&\n"
                    + "--------------------<>----''--\\\\---------\n"
                    + "                                \\\n";

    /** ASCII art used in the farewell. */
    private static final String ASCII_EXIT =
            "             ______         ___,\n"
                    + "              `--- \\   _))/.--`\n"
                    + "            ,__`--. \\/  '>--`\n"
                    + "----------- `._.-.      /``----------------\n"
                    + "                  '.__.'\n"
                    + "                   ' '\n";

    /** Chatbot name. */
    private final String chatbotName;

    /** Scanner used to read from System.in. */
    private final Scanner scanner;

    /**
     * Creates a Ui bound to System.in and System.out.
     *
     * @param chatbotName chatbot name to show in greetings.
     */
    public Ui(String chatbotName) {
        this.chatbotName = chatbotName;
        this.scanner = new Scanner(System.in);
    }

    // Scanner helpers

    /**
     * Reads a line of user input.
     *
     * @return the next input line, or null if no input is available.
     */
    public String read() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    // High-level messages

    /**
     * Prints the greeting banner and welcome text.
     */
    public String greetUser() {
        return ASCII_GREET
                + "Hi! " + chatbotName + " here.\n"
                + "What shall we do today?\n";
    }

    /**
     * Echoes a line back to the output.
     *
     * @param userInput text to print.
     */
    public String echoUser(String userInput) {
        return userInput;
    }

    /**
     * Prints the farewell banner and exit text.
     *
     * @return farewell message.
     */
    public String farewellUser() {
        return "Til next time!\n" + ASCII_EXIT;
    }

    /**
     * Prints a confirmation after adding a task.
     *
     * @param task added task.
     * @return task addition message.
     */
    public String showAddedTask(Task task) {
        return "TWEET! I've tucked this task into the nest:\n" + task;
    }

    /**
     * Prints a confirmation after deleting a task.
     *
     * @param task deleted task.
     * @return task deletion message.
     */
    public String showDeletedTask(Task task) {
        return "TWEET! I've removed this task:\n" + task;
    }

    /**
     * Prints the current status of a task.
     *
     * @param task task whose status changed.
     * @return task status update message.
     */
    public String showTaskStatus(Task task) {
        return ((task.getStatusIcon()).equals("X")
                ? "SWEET! I've marked this task as done:\n"
                : "ALRIGHTY, I've marked this task as not done yet:\n"
        ) + task;
    }

    /**
     * Returns a confirmation message after snoozing a task.
     *
     * @param task task that was updated.
     * @return confirmation message string.
     */
    public String showSnoozedTask(Task task) {
        return "ZZZ...\n" + task;
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param tasks task list whose size will be printed.
     * @return size of task list.
     */
    public String showTasksSize(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Prints all tasks in the list with 1-based indexing.
     *
     * @param tasks task list to display.
     * @return task list.
     */
    public String displayTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.getSize() == 0) {
            sb.append("CHIRRUP! The task list is empty.");
        } else {
            sb.append("CHIRP CHIRP! Let's get to work!").append(System.lineSeparator());
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                sb.append(i + 1).append(". ").append(task);
                if (i < tasks.getSize() - 1) {
                    sb.append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }


    /**
     * Displays tasks that contain the keyword of a find command.
     *
     * @param matches list of tasks that contain the keyword.
     * @return tasks that contain the keyword.
     */
    public String displayMatchingTasks(TaskList matches) {
        StringBuilder sb = new StringBuilder();
        if (matches.getSize() == 0) {
            sb.append("PEEP! Don't think that's in the nest!");
        } else {
            sb.append("Any of these the task you're looking for?").append(System.lineSeparator());
            for (int i = 0; i < matches.getSize(); i++) {
                sb.append(i + 1).append(". ").append(matches.getTask(i));
                if (i < matches.getSize() - 1) {
                    sb.append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }

    /**
     * Prints an error message verbatim.
     *
     * @param message error message to print.
     * @return error message.
     */
    public String showError(String message) {
        return message;
    }

}
