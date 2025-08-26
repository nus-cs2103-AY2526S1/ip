package minhgpt.ui;

import minhgpt.task.Task;
import minhgpt.task.TaskList;

/**
 * Responsible for all UI printing.
 */
public class Ui {
    // NOTE: PRIVATE

    /**
     * Print empty lines.
     *
     * @param size Number of empty lines to be printed.
     */
    private void padding(int size) {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += "\n";
        }
        System.out.print(str);
    }

    /**
     * Print the divider between each pair of input and response.
     */
    private void divider() {
        System.out.println("------------------------------------------");
    }

    // NOTE: PUBLIC

    /**
     * Print the UI when user enter the program.
     */
    public void printWelcome() {
        String logo = """
                  ███    ███ ██ ███    ██ ██   ██  ██████  ██████  ████████
                  ████  ████ ██ ████   ██ ██   ██ ██       ██   ██    ██
                  ██ ████ ██ ██ ██ ██  ██ ███████ ██   ███ ██████     ██
                  ██  ██  ██ ██ ██  ██ ██ ██   ██ ██    ██ ██         ██
                  ██      ██ ██ ██   ████ ██   ██  ██████  ██         ██
                """;
        padding(2);
        System.out.print(logo);
        padding(2);
        System.out.print("Hello! I'm MinhGPT.\nWhat can I do for you?");
        padding(2);
    }

    /**
     * Print the UI to prepare for next input from user.
     */
    public void printNext() {
        divider();
        System.out.print("Your input: ");
    }

    /**
     * Print the seperation between user input and program response.
     */
    public void printSeperate() {
        padding(1);
    }

    /**
     * Print the error message when the task with that index does not exist for mark / unmark and
     * delete operation.
     */
    public void printIndexError() {
        System.out.println("<( ⸝⸝•̀ - •́⸝⸝)> There is no tasks with that index."
                + " You could have caused an IndexOutOfBoundsException.");
    }

    /**
     * Print the UI when user add a task.
     *
     * @param task Task that was just added by user.
     */
    public void printAdd(Task task) {
        System.out.println(String.format("(˶ᵔ ᵕ ᵔ˶) Added: %s", task));
    }

    /**
     * Print the UI when user mark a task.
     *
     * @param task Task that was just marked by user.
     */
    public void printMark(Task task) {
        System.out.println("(˵˃ ᗜ ˂˵) Congrats on finishing the task.");
        System.out.println(task);
    }

    /**
     * Print the UI when user unmark a task.
     *
     * @param task Task that was just unmarked by user.
     */
    public void printUnmark(Task task) {
        System.out.println("(¬`‸´¬) Huh? Why did you lie?");
        System.out.println(task);
    }

    /**
     * Print the UI when user delete a task.
     *
     * @param task Task that was just deleted by user.
     */
    public void printDelete(Task task) {
        System.out.println(String.format("(˶ᵔ ᵕ ᵔ˶) Removed: %s", task));
    }

    /**
     * Print the UI when user list all tasks.
     */
    public void printList(TaskList tasks) {
        System.out.println(String.format(
                "(˶˃ ᵕ ˂˶) Here are the list of tasks. You have %d in total.", tasks.size()));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Print the UI when user exit the program.
     */
    public void printExit() {
        System.out.println("(╥﹏╥) Bye. Hope to see you again soon!");
    }
}
