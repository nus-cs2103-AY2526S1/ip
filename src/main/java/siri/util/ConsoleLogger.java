package siri.util;

import java.util.List;

import siri.exception.SiriException;
import siri.task.Task;


/**
 * ConsoleLogger class
 */
public class ConsoleLogger {
    private TaskManager taskManager;

    public ConsoleLogger(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     *Prints a greeting message from siri.siri to the console.
     */
    public StringBuilder PrintGreet() {
        StringBuilder res = new StringBuilder(" Hello! I'm siri.siri\n" + " What can I do for you?\n");
        System.out.print(res);
        return res;
    }

    /**
     *Prints an exit message from siri.siri to the console.
     */
    public StringBuilder PrintExit() {
        StringBuilder res = new StringBuilder(" Bye. Hope to see you again soon!\n");
        System.out.print(res);
        return res;
    }

    /**
     * Prints back the given word to the console as an echo message.
     *
     * @param word the input string to be echoed
     */
    public String Echo(String word) {
        String res = "siri heard: " + word + "\n";
        System.out.print(res);
        return res;
    }

    /**
     * Prints every task stored in the taskManger
     */
    public StringBuilder displayList() {
        StringBuilder res = new StringBuilder();
        res.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskManager.getCount(); i++) {
            res.append(i + 1).append(". ").append(taskManager.getTasks().get(i).display()).append("\n");
        }
        System.out.print(res);

        return res;
    }

    /**
     * mark a task
     * @param index the index of the task
     */
    public StringBuilder mark(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("siri.task.task does not exist");
        }
        Task t = taskManager.getTasks().get(index - 1);
        t.setDone(true);
        StringBuilder res = new StringBuilder();
        res.append("  Nice! I've marked this task as done:\n");
        res.append("  ");
        res.append(t.display());
        System.out.print(res);
        return res;
    }

    /**
     * unmark a task
     * @param index the index of the task
     */
    public StringBuilder unmark(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("siri.task.task does not exist");
        }
        Task t = taskManager.getTasks().get(index - 1);
        t.setDone(false);
        StringBuilder res = new StringBuilder();
        res.append("  Nice! I've marked this task as not done:\n");
        res.append("  ");
        res.append(t.display());
        System.out.print(res);
        return res;
    }

    /**
     * display the task added and show the number of tasks in the list
     * @param task the task added
     */
    public StringBuilder displayTask(Task task) {
        StringBuilder res = new StringBuilder();
        res.append("Got it. I've added this task:\n");
        res.append("  ").append(task.display()).append("\n");
        res.append("Now you have ").append(taskManager.getCount()).append(" tasks in the list.\n");
        System.out.print(res);
        return res;

    }

    /**
     * print one line of words
     * @param word words printed
     */
    public static StringBuilder printLine(String word) {
        StringBuilder res = new StringBuilder();
        res.append(word).append("\n");
        System.out.print(res);
        return res;
    }

    /**
     * Print each argument on its own line.
     * @param words lines to print
     * @return the accumulated text that was printed
     */
    public static StringBuilder printLines(String... words) {
        StringBuilder res = new StringBuilder();
        if (words != null) {
            for (String w : words) {
                res.append(String.valueOf(w)).append("\n");
            }
        }
        System.out.print(res);
        return res;
    }

    /**
     * display the task deleted and show the number of tasks in the list
     * @param index the index of the task deleted
     */
    public StringBuilder delete(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("siri.task.task does not exist");
        }
        StringBuilder res = new StringBuilder();
        res.append("Noted. I've removed this task:\n");
        res.append("  ").append(taskManager.getTasks().get(index - 1).display()).append("\n");
        taskManager.deleteTask(index - 1);
        res.append("Now you have ").append(taskManager.getCount()).append(" tasks in the list.\n");
        System.out.print(res);
        return res;
    }

    /**
     * display the tasks find
     * @param list tasklist
     * @return tasks find
     */
    public StringBuilder displayFind(List<Task> list) {
        if (list.isEmpty()) {
            System.out.print("Can not find matching tasks");
        }
        StringBuilder res = new StringBuilder();
        res.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            res.append(i + 1).append(". ").append(list.get(i).display()).append("\n");
        }
        System.out.print(res);
        return res;
    }
}
