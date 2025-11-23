package conversal.ui;

import java.util.ArrayList;
import java.util.Scanner;

import conversal.task.Task;

/**
 * Handles user I/O (welcome, bye, instructions, messages, results, and errors).
 */
public class Ui {

    /*  Constants  */

    private static final String NAME = "Conversal";
    private static final String GREETING = "What can I do for you?";
    private static final String EXIT = "Bye! Hope to see you again!";

    private static final String INSTRUCTION_BYE = "To close chatbot, enter: bye.";
    private static final String INSTRUCTION_LIST = "To display tasks list, enter: list";
    private static final String INSTRUCTION_FIND = "To find task(s) with keyword, enter: find (keyword)";
    private static final String INSTRUCTION_MARK = "To mark task as Complete, enter: mark (task no.)";
    private static final String INSTRUCTION_UNMARK = "To mark task as Incomplete, enter: unmark (task no.)";
    private static final String INSTRUCTION_DELETE = "To delete a task: delete (task no.)";
    private static final String INSTRUCTION_TODO = "To add Todo task, enter: todo (task)";
    private static final String INSTRUCTION_DEADLINE =
            "To add Deadline task, enter: deadline (task) /by (date in YYYY-MM-DD format)";
    private static final String INSTRUCTION_EVENT =
            "To add Event task, enter: event (task) /from (start) /to (end)";
    private static final String INSTRUCTION_DOWITHIN =
            "To add DoWithinPeriod task, enter: do-within (task) /within (period)";

    private final Scanner scanner;

    /** Creates a UI that reads from standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts user for their input and captures it.
     *
     * @return user input as a string
     */
    public String readInput() {
        System.out.print("User: ");
        return scanner.nextLine();
    }

    /** Calls greeting and instruction methods*/
    public void welcomeMessage() {
        printGreeting();
        printInstructions();
    }

    /** Prints welcome message */
    private void printGreeting() {
        System.out.println("Hello! I'm " + NAME + ".");
        System.out.println(GREETING + "\n");
    }

    /** Prints instruction messages */
    private void printInstructions() {
        System.out.println("Instructions: ");
        for (String line : instructionLines()) {
            System.out.println("> " + line);
        }
        System.out.println();
    }

    /** helpers */
    private java.util.List<String> instructionLines() {
        return java.util.List.of(
                INSTRUCTION_BYE,
                INSTRUCTION_LIST,
                INSTRUCTION_MARK,
                INSTRUCTION_UNMARK,
                INSTRUCTION_DELETE,
                INSTRUCTION_TODO,
                INSTRUCTION_DEADLINE,
                INSTRUCTION_EVENT,
                INSTRUCTION_DOWITHIN
        );
    }

    /** Returns the greeting + instructions as a single String (for GUI) */
    public String composeWelcomeText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm ").append(NAME).append(".\n");
        sb.append(GREETING).append("\n\n");
        sb.append("Instructions: \n");
        for (String line : instructionLines()) {
            sb.append("> ").append(line).append("\n");
        }
        return sb.toString();
    }

    /** Prints exit message. */
    public void exitMessage() {
        System.out.println("\n" + EXIT);
    }

    /**
     * Prints acknowledgement after adding a task.
     *
     * @param task        the task added
     * @param totalTasks  current number of tasks
     */
    public void addMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
    }

    /**
     * Shows list of tasks.
     *
     * @param tasks ArrayList of tasks
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println("\nHere is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            System.out.println((i + 1) + ". " + taskString);
        }
        System.out.println();
    }

    /**
     * Prints acknowledgement of marking or unmarking a task.
     *
     * @param task       the task that was marked or unmarked
     * @param isComplete true if the task was marked as complete, false if unmarked
     */
    public void acknowledge(Task task, boolean isComplete) {
        if (isComplete) {
            System.out.println("Nice! I've marked this task as complete:");
        } else {
            System.out.println("OK! I've marked this task as incomplete:");
        }
        System.out.println(task + "\n");
    }

    /**
     * Prints acknowledgement of a task being deleted, and the updated task count.
     *
     * @param size the number of tasks remaining in the list
     * @param name the string representation of the deleted task
     */
    public void deleteMessage(int size, String name) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + name);
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays tasks whose descriptions contain the keyword.
     *
     * @param matches the matching tasks
     */
    public void showFound(ArrayList<Task> matches) {
        System.out.println("\nHere are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + ". " + matches.get(i));
        }
        System.out.println();
    }

    /**
     * Prints an error message.
     *
     * @param message the message to be printed out
     */
    public void printError(String message) {
        System.out.println(message + "\n");
    }

    /** Closes the scanner resource. */
    public void close() {
        scanner.close();
    }

    /** Getters for instruction strings **/
    public String getInstructionFind() {
        return INSTRUCTION_FIND;
    }

    public String getInstructionMark() {
        return INSTRUCTION_MARK;
    }

    public String getInstructionUnmark() {
        return INSTRUCTION_UNMARK;
    }

    public String getInstructionDelete() {
        return INSTRUCTION_DELETE;
    }

    public String getInstructionTodo() {
        return INSTRUCTION_TODO;
    }

    public String getInstructionDeadline() {
        return INSTRUCTION_DEADLINE;
    }

    public String getInstructionEvent() {
        return INSTRUCTION_EVENT;
    }

    public String getInstructionDoWithin() {
        return INSTRUCTION_DOWITHIN;
    }
}
