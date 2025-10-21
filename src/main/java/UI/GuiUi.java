package UI;

import Task.TaskItem;
import Task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class GuiUi extends Ui {
    private final List<String> messages;

    public GuiUi() {
        messages = new ArrayList<>();
    }

    private void addMessage(String message) {
        messages.add(message);
    }

    public String getOutput() {
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            sb.append(msg).append("\n");
        }
        messages.clear();
        return sb.toString();
    }

    @Override
    public void showError(String errorMessage) {
        addMessage("Error: " + errorMessage);
    }

    @Override
    public void showWelcome() {
        addMessage("Hello! I'm John.John");
        addMessage("How may I assist you?");
    }

    @Override
    public void showBye() {
        addMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public void showAdded(TaskItem task, int size) {
        addMessage("Got it. I've added this task:");
        addMessage("  " + task);
        addMessage("Now you have " + size + " tasks in this list.");
    }

    @Override
    public void showList(TaskList tasks) {
        addMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            addMessage((i + 1) + ". " + tasks.getItem(i));
        }
    }

    @Override
    public void showMarked(TaskItem task) {
        addMessage("Nice! I've marked this task as done:");
        addMessage("  " + task);
    }

    @Override
    public void showUnmarked(TaskItem task) {
        addMessage("OK, I've marked this task as not done yet:");
        addMessage("  " + task);
    }

    @Override
    public void showDeleted(TaskItem task, int size) {
        addMessage("Noted. I've removed this task:");
        addMessage("  " + task);
        addMessage("Now you have " + size + " tasks in the list.");
    }
}