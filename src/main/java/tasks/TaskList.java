package tasks;

import exception.NicholasException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList handles storing of multiple tasks
 * <p>
 * Acts like a List class but has other methods unique for the nicholas chatbot application.
 */
public class TaskList {
    private final List<Task> items;

    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    public Task get(int idx) {
        return items.get(idx);
    }

    public int size() {
        return items.size();
    }

    public String addItem(Task task){
        String returnText = "Got it. I've added this task: \n";
        String returnAddItemMessage;

        items.add(task);
        returnAddItemMessage = returnText + task + "\n" + "Now you have " + items.size() + " tasks in the list.";

        return returnAddItemMessage;
    }

    public String getList() {
        String returnText = "Here are the tasks in your list: \n";

        for(int i = 0; i < items.size(); i++) {
           returnText = returnText + (i+1)  + "." + items.get(i).toString() + "\n";
        }

        return returnText;
    }

    public String findTask(String input) {
        int count = 1;
        String returnText = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().contains(input)) {
                returnText = returnText + count + "." + items.get(i).toString() + "\n";
                count++;
            }
        }
        return returnText;
    }

    public String markTaskAsDone(int idx) throws NicholasException {
        validateIndex(idx, "marking");
        return items.get(idx - 1).markAsDone();
    }

    public String markTaskAsUndone(int idx) throws NicholasException {
        validateIndex(idx, "marking");
        return items.get(idx - 1).markAsUndone();
    }

    public String deleteTask(int idx) throws NicholasException {
        validateIndex(idx, "deleting");
        String returnCmd = "Noted. I've removed this task: \n" + items.get(idx - 1)
                + "\n Now you have " + (items.size()-1) + " tasks in the list. \n";


        items.remove(idx - 1);

        return returnCmd;
    }

    public String tagTask(int idx, String tag) {
        String returnMsg = items.get(idx - 1).tagTask(tag);
        return returnMsg;
    }

    public void validateIndex(int idx, String action) throws NicholasException {
        String invalidateMessage;
        if (items.isEmpty()) {
            invalidateMessage = "Please add tasks before " + action;
            throw new NicholasException(invalidateMessage);
        }
        if (idx < 1 || idx > items.size()) {
            invalidateMessage = "Please enter a valid index from 1 to " + items.size();
            throw new NicholasException(invalidateMessage);
        }
    }

    @Override
    public String toString() {
        String tasksDisplay = "";
        tasksDisplay = items.stream()
                .map(x -> x.saveFileFormat() + "\n")
                .reduce(tasksDisplay, (x , y) -> x + y);

        return tasksDisplay;
    }
}
