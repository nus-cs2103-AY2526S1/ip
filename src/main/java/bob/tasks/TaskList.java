package bob.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class containing a list of Tasks to be noted
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds Task item to list
     * Prints confirmation message
     * @param item task to be added
     */
    public String addItem(Task item) {
        try {
            taskList.add(item);
            return ("Alright! Task added:\n"
                    + item + "\n"
                    + "There are now " + taskList.size() + " task(s) in the list.");
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds Task item to list
     * No message printed
     * @param item task to be added
     */
    public void fileAddItem(Task item) {
        try {
            taskList.add(item);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks item at index as complete
     * @param index tasked to be marked
     */
    public String markItem(int index) {
        boolean isValidIndex = index > 0 && index <= taskList.size();

        if (!isValidIndex) {
            return ("Invalid index!\n");
        }
        Task thing = this.taskList.get(index - 1);
        thing.mark();
        return("Ok the task is marked as complete!\n"
                + thing + "\n");
    }

    /**
     * Marks item at index as not complete
     * @param index task to be unmarked
     */
    public String unmarkItem(int index) {
        boolean isValidIndex = index > 0 && index <= taskList.size();

        if (!isValidIndex) {
            return ("Invalid index!\n");
        }
        Task thing = this.taskList.get(index - 1);
        thing.unmark();
        return("Ok the task is no longer complete!\n"
                + thing + "\n");
    }

    /**
     * Removes Task item at index form list
     * @param index tasked to be deleted
     */
    public String deleteItem(int index) {
        boolean isValidIndex = index > 0 && index <= taskList.size();

        if (!isValidIndex) {
            return ("Invalid index!\n");
        }
        Task item = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return("Alright! Task removed:\n" + item + "\n"
                + "There are now " + taskList.size() + " task(s) in the list.\n");
    }

    /**
     * Prints a list of tasks that contain provided query
     * @param query string to be matched with
     */
    public String findTask(String query) {
        int length = taskList.size();

        if (length < 1) {
            return("No items on the list!\n");
        }

        String out = "";
        for (int i = 0; i < length; i++) {
            Task currTask = taskList.get(i);
            if (!currTask.containsString(query)) {
                continue;
            }
            out += (i + 1) + "." + taskList.get(i) + "\n";

        }
        return(out.isEmpty() ? "No items match query" : out);
    }

    /**
     * Updates the description of an existing task in the list
     * @param index index of item to be updated
     * @param newDescription new description of item
     * @return status of update
     */
    public String updateDescription(int index, String newDescription) {
        try {
            Task task = this.getTask(index);
            task.setDescription(newDescription);
            return "Updated description:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Updates the due date of deadlines
     * @param index index of deadline to be updated
     * @param newDueDate new due date
     * @return status of update
     */
    public String updateDeadline(int index, LocalDate newDueDate) {
        try {
            Task task = this.getTask(index);
            if ( !(task instanceof Deadline deadline)) {
                return "Task at index " + index + " is not a deadline!\n";
            }
            deadline.setDueDate(newDueDate);
            return "Updated deadline:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Updates the start and end date of an event
     * @param index index of event to be updated
     * @param newStartDate new start date
     * @param newEndDate new end date
     * @return status of update
     */
    public String updateEvent(int index, LocalDate newStartDate, LocalDate newEndDate) {
        try {
            Task task = this.getTask(index);
            if ( !(task instanceof Event event)) {
                return "Task at index " + index + " is not an event!\n";
            }
            event.setStartDate(newStartDate);
            event.setEndDate(newEndDate);
            return "Updated event:\n" + task + "\n";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    private Task getTask(int index) {
        if (index <= 0 || index > taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return taskList.get(index - 1);
    }

    /**
     * Getter for arraylist
     * @return taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public boolean hasTasks() {
        return !taskList.isEmpty();
    }

    @Override
    public String toString() {
        int length = taskList.size();

        if (length < 1) {
            return("No items on the list!\n");
        }

        String out = "";
        for (int i = 0; i < length; i++) {
            out += (i + 1) + "." + taskList.get(i) + "\n";
        }
        return out;
    }
}
