package bestie;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles all interactions with the user, from reading commands to
 * presenting friendly status messages.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Creates a UI that reads input from {@link System#in}.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Writes the given message to the output stream.
     *
     * <p>Subclasses can override this to redirect how messages are surfaced
     * (e.g. storing them for the GUI) while keeping the formatting logic in
     * this class.</p>
     *
     * @param message content to display
     */
    protected void write(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    /**
     * Prints the welcome banner that greets the user at start-up.
     */
    public void showWelcome() {
        write(
                "____________________________________________________________",
                " heyyy I'm Bestie",
                " whatsup?",
                "____________________________________________________________"
        );
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return raw command text without validation
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the divider line used to separate output blocks.
     */
    public void showLine() {
        write("____________________________________________________________");
    }

    /**
     * Alerts the user that persistent data could not be loaded.
     */
    public void showLoadingError() {
        write("Bestie couldn't load tasks. Starting fresh.");
    }

    /**
     * Displays an error message while preserving Bestie's friendly tone.
     *
     * @param message explanation of the problem encountered
     */
    public void showError(String message) {
        write(" " + message);
    }

    /**
     * Says goodbye to the user before the application exits.
     */
    public void showBye() {
        write(" Bye bestie~ Keep slayin and prayin!");
    }

    /**
     * Prints every task in the list with a 1-based index.
     *
     * @param tasks collection of tasks to display
     */
    public void showList(TaskList tasks) {
        write(" Here is your task list bestie!");
        for (int i = 0; i < tasks.size(); i++) {
            write(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Announces that the specified task has been marked as complete.
     *
     * @param task the task that was marked
     */
    public void showMark(Task task) {
        write(" YAYYY ive marked:", "  " + task);
    }

    /**
     * Announces that the specified task has been unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showUnmark(Task task) {
        write(" no worries! ive unmarked:", "  " + task);
    }

    /**
     * Reports that a task has been deleted and shows the remaining count.
     *
     * @param task the task that was removed
     * @param size remaining number of tasks
     */
    public void showDelete(Task task, int size) {
        write(
                " Noted. I've removed this task:",
                "  " + task,
                " Now you have " + size + " tasks in the list."
        );
    }

    /**
     * Confirms a new task was added and reports the updated total.
     *
     * @param task the task that was just added
     * @param size total number of tasks after insertion
     */
    public void showAdd(Task task, int size) {
        write(
                " aightt ive added this task:",
                "  " + task,
                " now you have " + size + " tasks in your list!!"
        );
    }

    /**
     * Lets the user know that tags have been added to a task.
     *
     * @param task      the task that was tagged
     * @param addedTags tags newly added to the task (without the leading '#')
     */
    public void showTag(Task task, List<String> addedTags) {
        if (addedTags == null || addedTags.isEmpty()) {
            write(" looks like those tags were already there bestie!", "  " + task);
            return;
        }
        String formatted = addedTags.stream()
                .map(tag -> "#" + tag)
                .collect(Collectors.joining(" "));
        write(
                " yasss ive tagged it with:",
                "  " + formatted,
                "  " + task
        );
    }

    /**
     * Displays the search results returned by the find command.
     *
     * @param matches ordered list of tasks containing the keyword
     */
    public void showFindResults(List<Task> matches) {
        if (matches.isEmpty()) {
            write(" hmmm I couldn't find any matching tasks bestie!");
            return;
        }
        write(" Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            write(" " + (i + 1) + "." + matches.get(i));
        }
    }
}