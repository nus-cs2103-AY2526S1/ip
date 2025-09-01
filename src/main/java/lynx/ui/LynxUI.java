package lynx.ui;

/**
 * Contains general methods pertaining to the UI.
 */
public abstract class LynxUI {

    /**
     * Prints a line.
     */
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a line after a message.
     *
     * @param message Message to be printed.
     */
    public static void printLineAfter(String message) {
        System.out.println(message);
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
     * Returns a greeting.
     */
    public static String hello() {
        return "Hello! I'm Tasklynx. \n"
                + "Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.";
    }

    /**
     * Returns a farewell.
     */
    public static String bye() {
        return "Goodbye. I'll be here whenever you need to stay on track.";
    }

    /**
     * Returns the TaskLynx user guide as a string.
     */
    public static String printHelpGui() {
        return """
                TaskLynx User Guide:
                
                General commands:
                     reload                    - Load tasks from data file to task list (destructively)
                     save                      - Save task list to date file (destructively)
                     bye                       - Save and exit the program
                     * Creates a data file first if not found
                
                Task creation:
                     todo [name]
                     deadline [name] /by [date]
                     event [name] /from [date] /to [date]
                     * Date format: yyyy-mm-dd, yyyy-mm-dd-hh, or yyyy-mm-dd-hh-mm
                
                Task actions (requires one or more search modifier(s)):
                     list                      - Show task(s)
                     mark                      - Mark task(s) as done
                     unmark                    - Unmark task(s) as not done
                     delete                    - Remove task(s)
                
                Search modifiers:
                     /key [keyword]            - Tasks with names containing the keyword
                     /on [date]                - Tasks occurring on a specific date
                     /status [status]          - Tasks with matching status
                     /type [type]              - Tasks with matching type
                     /id [id]                  - Task with matching id
                     /all                      - All tasks in the task list
                     * Arguments must not contain blank characters, keywords included
                
                Example usage:
                     list /key meeting         - Show all tasks containing 'meeting'
                     list /on 2025-09-01       - Show tasks occurring on 1st September 2025
                     delete /status complete   - Delete all tasks marked as completed
                     delete /type todo         - Delete all todo tasks
                     mark /id 3                - Mark the task with id 3 as done
                     unmark /all               - Unmark all tasks as not done
                
                Advanced usage (chaining):
                     list /type deadline /status incomplete /on 2025-09-01 /key attendacodingcourse
                     * [D][I] Attend a Coding Course by John (by: Sep 1 2025 00:00) (id:1)
                """;
    }

}
