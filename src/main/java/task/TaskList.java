package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ui.Ui;

/**
 * Represents a list of tasks and provides methods to manage them.
 *
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if the given index is within the bounds of the task list.
     *
     * @param index The index to check (1-based)
     * @return <code>true</code> if the index is valid, <code>false</code> otherwise
     */
    public boolean isValidIndex(int index) {
        return index <= taskList.size();
    }

    /**
     * Adds a new todo task to the list and prints confirmation.
     *
     * @param task The description of the todo task
     */
    public void addTodo(String task) {
        Task newTask = new TodoTask(task);
        taskList.add(newTask);
        add(newTask);
    }

    /**
     * Adds a new deadline task to the list and prints confirmation.
     *
     * @param task     The description of the deadline task
     * @param deadline The deadline date
     */
    public void addDeadline(String task, LocalDate deadline) {
        Task newTask = new DeadlineTask(task, deadline);
        taskList.add(newTask);
        add(newTask);
    }

    /**
     * Adds a new event task to the list and prints confirmation.
     *
     * @param task The description of the event
     * @param from The start date
     * @param to   The end date
     */
    public void addEvent(String task, LocalDate from, LocalDate to) {
        Task newTask = new EventTask(task, from, to);
        taskList.add(newTask);
        add(newTask);
    }

    /**
     * Prints a confirmation message after adding a task.
     *
     * @param task The task that was added
     */
    private void add(Task task) {
        Ui.printSuccess("Got it. I've added this task:\n"
                + "" + task.toString() + "\n"
                + "Now you have " + taskCount() + " in the list.\n");
    }

    private String taskCount() {
        int count = taskList.size();
        if (count == 1) {
            return count + " task";
        } else {
            return count + " tasks";
        }
    }

    /**
     * Prints all tasks in the list, or a message if the list is empty.
     */
    public void list() {
        if (taskList.isEmpty()) {
            Ui.printWarning("Your list is empty.");
            return;
        }
        StringBuilder listMessage = new StringBuilder(
                "Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            listMessage.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        Ui.printInfo(listMessage.toString());
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param indexString The 1-based index of the task, as a string
     */
    public void mark(String indexString) {
        Task task = taskList.get(Integer.parseInt(indexString) - 1);
        task.mark();
        Ui.printSuccess("Nice! I've marked this task as done:\n"
                 + task.toString() + "\n");
    }

    /**
     * Unmarks the task at the given index as not done.
     *
     * @param indexString The 1-based index of the task, as a string
     */
    public void unmark(String indexString) {
        Task task = taskList.get(Integer.parseInt(indexString) - 1);
        task.unmark();
        Ui.printSuccess("OK, I've marked this task as not done yet:\n"
                 + task.toString() + "\n");
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param indexString The 1-based index of the task, as a string
     */
    public void delete(String indexString) {
        Task task = taskList.get(Integer.parseInt(indexString) - 1);
        taskList.remove(task);
        Ui.printSuccess("OK, I've deleted this task:\n"
                 + task.toString() + "\n");
    }

    /**
     * Returns the save string representation of all tasks in the list.
     *
     * @return A string containing the save data of all tasks, line by line
     */
    public String save() {
        StringBuilder str = new StringBuilder();
        for (Task i : taskList) {
            str.append(i.save())
                    .append("\n");
        }
        return str.toString();
    }

    /**
     * Prints the tasks that match the description provided.
     *
     * @param string the description of the task to search for
     */
    public void find(String string) {
        StringBuilder listMessage = new StringBuilder(
                "Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(string)) {
                listMessage.append(count)
                        .append(". ")
                        .append(taskList.get(i).toString())
                        .append("\n");
                count++;
            }
        }
        if (count == 1) {
            Ui.printWarning("No task in your list matches your search.");
            return;
        }
        Ui.printInfo(listMessage.toString());
    }

    /**
     * Prints upcoming tasks that are not marked completed
     */
    public void remind() {
        LocalDate today = LocalDate.now();
        StringBuilder listString = new StringBuilder();


        List<Task> reminders = taskList.stream()
                .filter(task -> task.isUpcoming(today))
                .filter(task -> !task.getIsCompleted())
                .sorted(Comparator.comparing(task -> ((GetDateable) task).getDate()))
                .toList();


        if (reminders.isEmpty()) {
            Ui.printSuccess("You have no upcoming tasks :)");
            return;
        }

        listString.append("Reminders for your upcoming tasks! \n");


        for (int i = 0; i < reminders.size(); i++) {
            listString.append(i + 1)
                    .append(". ")
                    .append(reminders.get(i).toString())
                    .append("\n");
        }

        Ui.printWarning(listString.toString());
    }

}
