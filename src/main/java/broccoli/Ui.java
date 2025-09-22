package broccoli;

import java.util.Scanner;

/**
 * Handles user interface interactions and display formatting.
 */
public class Ui {
    private Scanner scanner;
    private String horizontalLine;
    private static final String HELP_MESSAGE = "To add a task:\n" +
                    "1. Specify the task type with:\n" +
                    "   * todo + task name eg: todo finish lec quiz\n" +
                    "   * deadline + task name + /by + DD/MM/YYYY/time\n" +
                    "   * event + task name + /from + time + /to + time\n\n" +
                    "To check/modify the task list:\n" +
                    "1. list : display the list of tasks\n" +
                    "2. mark + task number: mark the respective task with X in front\n" +
                    "3. unmark + task number: unmark the respective task clearing X in front\n" +
                    "4. delete + task number: delete the respective task from the task list\n" +
                    "5. find + key words: sort out respective task with task description match with the key words";

    private static final String GREETING_MESSAGE = "Hello! Hello! Hello! I'm Broccoli.Your Green Task Buddy!\n" +
                                                   "Tell me what you gonna do and I will help you track them!\n" +
                                                    "Enter (help), I will let you know how to use the app";

    private static final String EXITING_MESSAGE = "Bye! Wish you come back with tasks done!\n";

    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the greeting message displayed to users when the application starts.
     * Provides a welcoming introduction to the Broccoli task management system.
     *
     * @return A string containing the greeting message with usage instructions
     */
 public String greeting() {
     return GREETING_MESSAGE;
 }

    /**
     * Returns the goodbye message displayed when users exit the application.
     * Provides a friendly farewell message encouraging task completion.
     *
     * @return A string containing the exit message
     */
 public String exiting() {
     return EXITING_MESSAGE;
 }

    /**
     * Returns a formatted display of all tasks in the provided task list.
     * Includes a motivational header message followed by the numbered task list.
     *
     * @param taskList The TaskList containing tasks to be displayed
     * @return A string containing the formatted task list with header message
     */
    public String displayList(TaskList taskList) {
        if (taskList.isEmptyList()) {
            return "There are no tasks on track!";
        } else {
            return "Quickly go and finish all the UNDONE tasks!" + "\n" + taskList.displayTask();
        }
    }


    /**
     * Returns the help message containing usage instructions for the application.
     * Provides detailed information about available commands and their syntax.
     *
     * @return A string containing comprehensive help documentation
     */
    public String displayHelpMessage(){
        return HELP_MESSAGE;
    }

}


