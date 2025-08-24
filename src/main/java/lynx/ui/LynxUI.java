package lynx.ui;

/**
 * Class containing general methods pertaining to the UI.
 */
public abstract class LynxUI {

    /**
     * Prints a line.
     */
    private static final String LINE = "____________________________________________________________";

    public static void line() {
        System.out.println(LINE);
    }

    /**
     * Prints a line before and after a message.
     *
     * @param message Message to be printed.
     */
    public static void printBox(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints a greeting.
     */
    public static void hello() {
        LynxUI.printBox("Hello! I'm Tasklynx. \n" +
                "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
    }

    /**
     * Prints a farewell.
     */
    public static void bye() {
        LynxUI.printBox("Goodbye. I'll be here whenever you need to stay on track.");
    }

    /**
     * Prints the TaskLynx user guide.
     */
    public static void printHelp() {
        LynxUI.line();
        System.out.println("TaskLynx User Guide:");
        System.out.println();
        System.out.println("General commands:");
        System.out.println("     bye                    - Exit the program");
        System.out.println("     reload                 - Reload tasks from the data file (or create it if not found)");
        System.out.println();
        System.out.println("Task creation:");
        System.out.println("     todo [name]");
        System.out.println("     deadline [name] /by [date]");
        System.out.println("     event [name] /from [date] /to [date]");
        System.out.println("     * Date format: yyyy-mm-dd, yyyy-mm-dd-hh, or yyyy-mm-dd-hh-mm");
        System.out.println();
        System.out.println("Task actions (requires a search modifier):");
        System.out.println("     list                   - Show task(s)");
        System.out.println("     mark                   - Mark task(s) as done");
        System.out.println("     unmark                 - Unmark task(s) as not done");
        System.out.println("     delete                 - Remove task(s)");
        System.out.println();
        System.out.println("Search modifiers:");
        System.out.println("     [keyword]              - Tasks with names containing the keyword");
        System.out.println("     /on [date]             - Tasks occurring on a specific date");
        System.out.println("     /id [id]               - Task with matching id");
        System.out.println("     /all                   - All tasks in the task list");
        System.out.println();
        System.out.println("Example usage:");
        System.out.println("     list meeting           - Show all tasks containing 'meeting'");
        System.out.println("     list /on 2025-09-01    - Show tasks occurring on 1st September 2025");
        System.out.println("     mark /id 3             - Mark the task with id 3 as done");
        System.out.println("     mark /all              - Mark all tasks in the task list as done");
        LynxUI.line();
    }

}
