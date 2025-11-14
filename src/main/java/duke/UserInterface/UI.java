package duke.userinterface;

import duke.task.Task;
import duke.task.Tasklist;

/**
 * UI class to handle interactions with the user.
 */
public class UI {

    private String logo = ""
            + "     _   _      _                 \n"
            + "    | \\ | | ___ | | ___  ___  ___ \n"
            + "    |  \\| |/ _ \\| |/ __|/ _ \\|  _ \\\n"
            + "    | |\\  |  __/| |__ \\  (_) | | | |\n"
            + "    |_| \\_|\\___ |_|___/ \\___/|_| |_|\n";

    /**
     * Prints the welcome message.
     */
    public String welcome() {
        return "    ____________________________________\n"
             + "     Hello! I'm\n" + logo + "\n"
             + "     What can I do for you?\n"
             + "    ____________________________________";
    }

    /**
     * Prints the welcome message for gui.
     */
    public String welcomeGui() {
        return "    ____________________________________\n"
             + "     Hello! I'm NELSON\n"
             + "     What can I do for you?\n"
             + "    ____________________________________";
    }

    /**
     * Prints acknowledgement of added task.
     *
     * @param t the task added
     */
    public String addToListEB(Task t) {
        return "    ____________________________________\n"
             + "     Got it. I've added this task:\n"
             + "      " + t + "\n"
             + "     Now you have " + Tasklist.listSize() + " tasks in the list\n"
             + "    ____________________________________";
    }

    /**
     * Prints goodbye message.
     */
    public String bye() {
        return "    ____________________________________\n"
             + "    Bye. Hope to see you again soon!\n"
             + "    ____________________________________";
    }

    /**
     * Prints acknowledgement of task removal.
     *
     * @param t task to be removed
     */
    public String removeFromListEB(Task t) {
        return "    ____________________________________\n"
            + "     Noted. I've removed this task:\n"
            + "       " + t + "\n"
            + "     Now you have " + Tasklist.listSize() + " tasks in the list.\n"
            + "    ____________________________________";
    }

    /**
     * Prints acknowledgement of task completion.
     *
     * @param i index of task marked as done
     */
    public String tickboxEB(int i) {
        Task t = Tasklist.peekList(i - 1);
        return "    ____________________________________\n"
             + "     Nice! I've marked this task as done:\n"
             + "     " + t + "\n"
             + "    ____________________________________";
    }

    /**
     * Prints acknowledgement of task unmark.
     *
     * @param i index of task marked as not done
     */
    public String untickboxEB(int i) {
        Task t = Tasklist.peekList(i - 1);
        return "    ____________________________________\n"
             + "     OK, I've marked this task as not done yet:\n"
             + "     " + t + "\n"
             + "    ____________________________________";
    }

    /**
     * Prints acknowledgement of setting the priority of a task.
     *
     * @param index index of task to set priority
     * @param priority priority level of task
     */
    public String setPriorityEB(int index, String priority) {
        return "    ____________________________________\n"
             + "     Priority of task at index " + index + " set to " + priority
             + "    ____________________________________";
    }

    // Error methods
    public void markError() throws BotException {
        throw new BotException("OOPS!!! Mark needs a task number.");
    }

    public void unmarkError() throws BotException {
        throw new BotException("OOPS!!! Unmark needs a task number.");
    }

    public void deleteError() throws BotException {
        throw new BotException("OOPS!!! Delete needs a task number.");
    }

    public void todoError() throws BotException {
        throw new BotException("OOPS!!! The description of a Todo cannot be empty.");
    }

    public void deadlineError() throws BotException {
        throw new BotException("OOPS!!! The description of a Deadline cannot be empty.");
    }

    public void eventError() throws BotException {
        throw new BotException("OOPS!!! The description of an Event cannot be empty.");
    }

    public void unknownError() throws BotException {
        throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void priorityError() throws BotException {
        throw new BotException("OOPS!!! Priority index and value cannot be empty.");
    }
}
