package mikey.ui;

import mikey.task.Task;
import mikey.task.TaskList;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Ui {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public String greet() {
        return ("Hello, I'm Mikey! \nWhat can I do for you today?");
    }

    public String bye() {
        return ("Bye. Hope to see you again soon!");
    }

    public String printError(String message) {
        return (message);
    }

    /**
     * Prints the list of tasks
     * @param t TaskList to be printed
     * @return String to be printed
     */
    public String printTasks(TaskList t) {
        assert t != null : "TaskList must not be null";

        if (t.getList().isEmpty()) {
            return ("No tasks yet!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 0; i < t.getList().size(); i++) {
            int index = i + 1;
            Task currTask = t.getList().get(i);
            sb.append("\n");
            sb.append("  " + index + ". " + currTask);
        }
        return sb.toString();
    }

    /**
     * Prints the message upon deleting a task
     * @param t Deleted task
     * @return String to be printed
     */
    public String printDeleteTask(Task t) {
        if (t != null) {
            return ("Noted. I've removed this task: \n" + t.toString());
        } else {
            return ("Task does not exist!");
        }
    }

    /**
     * Prints the message upon adding a task
     * @param t Added task
     * @return String to be printed
     */
    public String printAddTask(Task t, TaskList l) {
        return ("Got it, I've added this task: \n" + t.toString()
                + "\nNow you have " + l.getList().size() + " tasks in the list.");
    }

    /**
     * Prints the message upon marking a task as done
     * @param t Marked task
     * @return String to be printed
     */
    public String printMarkTask(Task t) {
        if (t != null) {
            return ("Nice! I've marked this task as done:\n" + t.toString());
        } else {
            return ("Task does not exist!");
        }
    }

    /**
     * Prints the message upon marking a task as not done
     * @param t Unmarked task
     * @return String to be printed
     */
    public String printUnmarkTask(Task t) {
        if (t != null) {
            return ("Ok, I've marked this task as not done yet:\n" + t.toString());
        } else {
            return ("Task does not exist!");
        }
    }

    /**
     * Prints the message when finding a task
     * @param l List of tasks
     * @return
     */
    public String printFoundTasks(List<Task> l) {
        if (l.isEmpty()) {
            return ("No matching tasks found!");
        } else {
            String result = ("Here are the matching tasks in your list: \n");
            return result + l.stream().map(t -> t.toString()).collect(Collectors.joining("\n"));
        }
    }

    /**
     * Prints the message when tagging a task
     * @param t Tagged task
     * @return
     */
    public String printTagTask(Task t) {
        if (t != null) {
            return ("OK, I've tagged this task:\n" + t.toString());
        } else {
            return ("Task does not exist!");
        }
    }

}
