package conversal.ui;

import java.util.ArrayList;

import conversal.task.Task;

/**
 * A UI variant that temporarily stores output messages for display in the GUI.
 *
 * Instead of printing to the console, this class stores lines in a buffer.
 * The line to be printed can then be retrieved and shown
 * inside the GUI dialog boxes.
 *
 */
public class GuiUi extends Ui {
    private final StringBuilder out = new StringBuilder();

    /**
     * Appends a line of text to the buffer.
     *
     * @param s the line to append
     */
    private void line(String s) {
        out.append(s).append(System.lineSeparator());
    }

    /**
     * Returns the current contents of the buffer and clears it.
     *
     * @return the buffered output as a single string
     */
    public String flush() {
        String s = out.toString();
        out.setLength(0);
        return s;
    }

    @Override
    public void welcomeMessage() {
        line(composeWelcomeText());
    }

    @Override public void exitMessage() {
        line("Bye! Hope to see you again! Click the red X to exit!");
    }

    @Override public void addMessage(Task task, int totalTasks) {
        line("Got it. I've added this task:");
        line(task.toString());
        line("Now you have " + totalTasks + " tasks in the list.");
    }

    @Override public void showList(ArrayList<Task> tasks) {
        line("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            line((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override public void acknowledge(Task task, boolean isComplete) {
        line(isComplete ? "Nice! I've marked this task as complete:"
                : "OK! I've marked this task as incomplete:");
        line(task.toString());
    }

    @Override public void deleteMessage(int size, String name) {
        line("Noted. I've removed this task:");
        line("    " + name);
        line("Now you have " + size + " tasks in the list.");
    }

    @Override public void showFound(ArrayList<Task> matches) {
        line("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            line((i + 1) + ". " + matches.get(i));
        }
    }

    @Override public void printError(String message) {
        line(message);
    }
}
