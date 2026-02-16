package core;

/**
 * Handles user interface operations for the TronaldDump chatbot.
 * Provides methods for displaying messages, tasks, and user interactions.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "________________________________\n";

    /**
     * Constructor for Ui class.
     */
    public Ui() {
    }

    /**
     * Displays all tasks in the task list.
     * @param taskList The list of tasks to display
     */
    public static void showAllTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.print("YOU HAVE NO TASKS IN YOUR LIST!\n");
        } else {
            System.out.print("HERE ARE THE TASKS IN YOUR LIST:\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println(String.format("%d. %s", i + 1, taskList.getTask(i).toString()));
            }
        }
        showHorizontalLine();
    }

    /**
     * Displays a message when a task is added.
     * @param taskList The list containing the newly added task
     */
    public static void showAddTask(TaskList taskList) {
        String successMessage = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s",
                taskList.getTask(taskList.getSize() - 1).toString());
        System.out.println(successMessage);

        String currentTaskCountMessage = String.format("NOW YOU HAVE %d %s IN THE LIST.\n",
                taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS");
        System.out.println(currentTaskCountMessage);
        showHorizontalLine();
    }

    /**
     * Displays a message when a task is marked as done.
     * @param taskList The list containing the task
     * @param taskNumber The index of the task that was marked
     */
    public static void showMarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
    }

    /**
     * Displays a message when a task is unmarked.
     * @param taskList The list containing the task
     * @param taskNumber The index of the task that was unmarked
     */
    public static void showUnmarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
    }

    /**
     * Displays a message when a task is deleted.
     * @param taskList The list after the task was removed
     * @param removedTask The task that was removed
     * @param taskNumber The index of the task that was removed
     */
    public static void showDeleteTask(TaskList taskList, Task removedTask, int taskNumber) {
        System.out.println(String.format("TASK %d HAS BEEN REMOVED:\n%s",
                taskNumber + 1, removedTask));
        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n",
                taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS"));
        showHorizontalLine();
    }

    /**
     * Displays matching tasks from a search.
     * @param matchingTasks The list of tasks that match the search criteria
     */
    public static void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.print("NO MATCHING TASKS FOUND!\n");
        } else {
            System.out.print("HERE ARE THE MATCHING TASKS IN YOUR LIST:\n");
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                System.out.println(String.format("%d. %s", i + 1, matchingTasks.getTask(i).toString()));
            }
        }
        showHorizontalLine();
    }

    /**
     * Displays all tasks in the task list after sorting.
     * @param taskList The list of tasks to display (already sorted)
     */
    public static void showSortedTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.print("YOU HAVE NO TASKS IN YOUR LIST!\n");
        } else {
            System.out.print("HERE ARE YOUR TASKS SORTED:\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println(String.format("%d. %s", i + 1, taskList.getTask(i).toString()));
            }
        }
        showHorizontalLine();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void showWelcome() {
        showHorizontalLine();
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        showHorizontalLine();
    }

    /**
     * Returns the welcome message as a string for GUI use.
     * @return The welcome message string
     */
    public static String getWelcomeMessage() {
        return "GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?";
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public static void showGoodbyeMessage() {
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns the goodbye message as a string for GUI use.
     * @return The goodbye message string
     */
    public static String getGoodbyeMessage() {
        return "THANK YOU FOR YOUR ATTENTION TO THIS MATTER!";
    }

    /**
     * Displays an error message.
     * @param message The error message to display
     */
    public static void showError(String message) {
        System.out.println(message);
        showHorizontalLine();
    }
}
