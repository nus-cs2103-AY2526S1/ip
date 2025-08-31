package piper.ui;

import java.util.Scanner;
import piper.task.TaskList;
import piper.task.Task;

/**
 * Handles user interaction via the console.
 * Provides methods to read user input and print messages.
 */
public class Ui {
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
        try {
            scanner.close();
        } catch (IllegalStateException ignored) { }
    }

    // ASCII art

    /** ASCII art used in the greeting. */
    private static final String ASCII_GREET =
            " _______\n" +
                    "|_   __ \\ ( ) .\\\n" +
                    "  | |__) |__  | ''\\   .---.  _ .--.\n" +
                    "  |  ___/[  | | /'\\\\ / /__\\\\[  /''\\|\n" +
                    " _| |_    | | | \\_/ || \\__.'| |\n" +
                    "|_____|  [___]| |\\_/  \\___/[___]\n" +
                    "           / /| | \\\n" +
                    "           [ \\] |  ]\n" +
                    "            \\__ __/      (l_\n" +
                    "              | |        <'  }\n" +
                    "            [___/        (  (_\\.&\n" +
                    "--------------------<>----''--\\\\---------\n" +
                    "                                \\\n";

    /** ASCII art used in the farewell. */
    private static final String ASCII_EXIT =
            "             ______         ___,\n" +
                    "              `--- \\   _))/.--`\n" +
                    "            ,__`--. \\/  '>--`\n" +
                    "----------- `._.-.      /``----------------\n" +
                    "                  '.__.'\n" +
                    "                   ' '\n";

    // High-level messages

    /**
     * Prints the greeting banner and welcome text.
     */
    public void greetUser() {
        System.out.println(
                ASCII_GREET +
                        "Hi! " + chatbotName + " here.\n" +
                        "What shall we do today?\n"
        );
    }

    /**
     * Echoes a line back to the output.
     *
     * @param userInput text to print.
     */
    public void echoUser(String userInput) {
        System.out.println(userInput);
    }

    /**
     * Prints the farewell banner and exit text.
     */
    public void farewellUser() {
        System.out.println(
                "Til next time!\n" + ASCII_EXIT
        );
    }

    /**
     * Prints a confirmation after adding a task.
     *
     * @param task added task.
     */
    public void showAddedTask(Task task) {
        System.out.println(
                "TWEET! I've tucked this task into the nest:\n" + task);
    }

    /**
     * Prints the current status of a task.
     *
     * @param task task whose status changed.
     */
    public void showTaskStatus(Task task) {
        System.out.println(
                ((task.getStatusIcon()).equals("X")
                        ? "SWEET! I've marked this task as done:\n"
                        : "ALRIGHTY, I've marked this task as not done yet:\n") +
                        task
        );
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param tasks task list whose size will be printed.
     */
    public void showTasksSize(TaskList tasks) {
        System.out.println(
                "Now you have " + tasks.getSize() + " tasks in the list."
        );
    }

    /**
     * Prints all tasks in the list with 1-based indexing.
     *
     * @param tasks task list to display.
     */
    public void displayTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("CHIRRUP! The task list is empty.");
        } else {
            System.out.println("CHIRP CHIRP! Let's get to work!");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.println(
                    (i + 1) + "." + task
            );
        }
    }

    /**
     * Prints an error message verbatim.
     *
     * @param message error message to print.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a confirmation after deleting a task.
     *
     * @param task deleted task.
     */
    public void showDeletedTask(Task task) {
        System.out.println("TWEET! I've removed this task:\n" + task);
    }
}