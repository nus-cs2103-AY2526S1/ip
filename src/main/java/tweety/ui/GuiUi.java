package tweety.ui;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

public class GuiUi extends Ui {
    private StringBuilder response;

    /**
     * Constructs a new GuiUi instance.
     * Initializes the response builder for capturing output.
     */
    public GuiUi() {
        super();
        this.response = new StringBuilder();
    }

    /**
     * Returns the captured response and clears the buffer.
     *
     * @return The formatted response string.
     */
    public String getResponse() {
        String result = response.toString().trim();
        response.setLength(0); // Clear the buffer for next use
        return result;
    }

    @Override
    public void printWelcomeMessage() {
        response.append("Hi! I tawt I taw a new user!\n");
        response.append("What can I do faw you, hon?\n");
    }

    @Override
    public void printExitMessage() {
        response.append("Aww, you gotta go? Bye bye! Hope to see ya soon!\n");
    }

    @Override
    public void printTaskList(TaskList tasks) {
        response.append("Here awe da tasks in your list, hon:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            response.append((i + 1)).append(". ").append(currTask).append("\n");
        }
    }

    @Override
    public void printNoTaskLeft() {
        response.append("Nice! You don't have any tasks due at da moment! Yay!\n");
    }

    @Override
    public void printAddedTask(Task addedTask, int taskCount) {
        response.append("Got it! I’ve added dis task faw ya:\n");
        response.append("  ").append(addedTask).append("\n");
        response.append("Now ya have ").append(taskCount).append(" tasks in your list, aww!\n");
    }

    @Override
    public void printDeletedTask(Task deletedTask, int taskCount) {
        response.append("Noted! I’ve removed dis task faw ya:\n");
        response.append("  ").append(deletedTask).append("\n");
        response.append("Now ya have ").append(taskCount).append(" tasks in the list, aww!\n");
    }

    @Override
    public void printUnmarkedTask(Task unmarkedTask) {
        response.append("OK! I’ve marked dis task as not done yet, hon:\n");
        response.append("  ").append(unmarkedTask).append("\n");
    }

    @Override
    public void printMarkedTask(Task markedTask) {
        response.append("Nice! I’ve marked dis task as done, yay!\n");
        response.append("  ").append(markedTask).append("\n");
    }

    @Override
    public void printFindTask(TaskList tasks) {
        response.append("Here awe da matching tasks in your list, hon:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            response.append((i + 1)).append(". ").append(currTask).append("\n");
        }
    }

    @Override
    public void printEditedTask(Task editedTask) {
        response.append("Nice! I’ve edited dis task for ya:\n");
        response.append("  ").append(editedTask).append("\n");
    }

    @Override
    public void printErrorMessage(Exception e) {
        response.append(e.getMessage()).append("\n");
    }
}
