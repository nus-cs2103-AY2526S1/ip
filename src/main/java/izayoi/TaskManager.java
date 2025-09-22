package izayoi;

import java.util.ArrayList;
import java.util.List;

import izayoi.input.InputReader;
import izayoi.task.Actionable;

/**
 * Manages tasks for the program
 * @author POeticPotatoes
 */
public class TaskManager implements Commandifiable {
    private final List<Actionable> tasks = new ArrayList<>();

    /**
     * Adds a task to the list of tasks
     * @param task the task to be added
     */
    public String addTask(Actionable task) {
        this.tasks.add(task);
        return String.format("Ok, I've added the task:\n%s\nYou'll have %s tasks now.",
                    task, tasks.size());
    }

    /**
     * Marks a task as done
     * @param index of the task to complete
     * @return the result of marking the task
     */
    public String markTask(int index) {
        if (index < 1 || index > this.tasks.size()) {
            return "Provided index was out of bounds. Is there something wrong with your head?";
        }
        Actionable t = tasks.get(index - 1);
        assert(t != null);
        t.markAsDone();
        return "Understood, the following task was executed:\n" + t;
    }

    /**
     * Marks a task as undone
     * @param index of the task to uncomplete
     * @return the result of marking the task
     */
    public String unmarkTask(int index) {
        if (index < 1 || index > this.tasks.size()) {
            return "Provided index was out of bounds. Is there something wrong with your head?";
        }
        Actionable t = tasks.get(index - 1);
        assert(t != null);
        t.markAsNotDone();
        return "Understood. I'll take note of your incompetence:\n" + t;
    }

    /**
     * Deletes a task from the manager
     * @param index the index of the task to be deleted
     * @return a status message for the deletion operation
     */
    public String deleteTask(int index) {
        if (index < 1 || index > this.tasks.size()) {
            return "How could you be asking me to delete something that doesn't exist??";
        }
        Actionable t = tasks.get(index - 1);
        assert(t != null);
        tasks.remove(index - 1);
        return "Understood. The following task has been eliminated:\n" + t;
    }

    /**
     * Finds all tasks with descriptions that match the provided query
     * @param input the InputReader holding the search query
     * @return a formatted string listing all matching tasks and their indexes
     */
    public String findTask(InputReader input) {
        String query = input.getTask().get("message");
        List<Actionable> result = new ArrayList<>();
        for (Actionable a: tasks) {
            if (a.toString().contains(query)) {
                result.add(a);
            }
        }
        return "This is all I could find:\n" + listTasks(result);
    }


    /**
     * Returns an indexed list of tasks as a printable String
     * @param t the tasks to be listed
     * @return the formatted string
     */
    private String listTasks(List<Actionable> t) {
        String result = "";
        for (Actionable a: t) {
            result += String.format("%s. %s\n", tasks.indexOf(a) + 1, a);
        }
        return result.trim();
    }

    @Override
    public List<String> commandify() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            Actionable t = tasks.get(i - 1);
            assert(t instanceof Commandifiable);
            result.addAll(((Commandifiable) t).commandify());
            if (t.isCompleted()) {
                result.add("mark " + i);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Here you go, your list of tasks:\n" + listTasks(tasks);
    }
}
