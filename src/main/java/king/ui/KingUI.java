package king.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import king.KingException;
import king.task.Deadline;
import king.task.Task;

/**
 * UI Manager for the King that helps with printing of statements
 */
public class KingUI {
    private final String spacer = "    ";

    /**
     * Show introduction message at start of conversation
     */
    public String showIntroduction() {
        return ("Greetings, subject! I am King, sovereign of thine tasks.\n"
                + "Speak thy command! Use `help` shouldst thou require guidance.");
    }

    /**
     * Show help message for help command
     */
    public String showHelp() {
        return "You seek counsel? Behold, the royal decrees thou may utter:\n"
                + " list [/sorted] - Present before me all tasks in thine service [or ranked by descending priority]\n"
                + " due [YYYY-MM-DD] - Reveal unto me tasks due upon a chosen date\n"
                + " find [string1] [string2] ... - Summon tasks whose words match thy search\n"
                + " todo [task name] /priority [VL (Very Low) / L / M / H / VH (Very High)] - "
                + "Command me to create a humble todo\n"
                + " deadline [task name] /priority [VL (Very Low) / L / M / H / VH (Very High)] "
                + "/by [YYYY-MM-DD] - Declare a task with a fated deadline\n"
                + " event [task name] /priority [VL (Very Low) / L / M / H / VH (Very High)] "
                + "/from [YYYY-MM-DD] /to [YYYY-MM-DD] - Proclaim an event spanning a period\n"
                + " mark [index] - Mark a task as triumphant and complete\n"
                + " unmark [index] - Return a task to its unfinished state\n"
                + " delete [index] - Banish a task from thy sight\n"
                + " clear - Banish all tasks from thy sight\n"
                + " bye - Take thy leave of mine audience\n"
                + " help - Summon once more this scroll of commands";
    }

    /**
     * Show all tasks for list command
     *
     * @param list List of all tasks
     */
    public String showAllList(ArrayList<Task> list) {
        String response = "Behold! These are the tasks that dwell within thy service:\n";
        for (int i = 1; i <= list.size(); i++) {
            response = response.concat(i + ". " + list.get(i - 1) + "\n");
        }
        return response;
    }

    /**
     * Show specific tasks due for due command
     *
     * @param list List of all tasks
     * @param date Date of task due
     */
    public String showDueList(ArrayList<Task> list, LocalDate date) {
        StringBuilder response = new StringBuilder("Tasks decreed for "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " are as follows:\n");
        list.stream()
                .filter(task -> task.getType() == Task.Type.DEADLINE)
                .filter(task -> ((Deadline) task).getBy().isEqual(date))
                .forEach(task ->
                        response.append(list.indexOf(task) + 1)
                                .append(". ")
                                .append(task)
                                .append("\n"));

        return response.toString();
    }

    /**
     * Show specific tasks due for due command
     *
     * @param list List of all tasks
     * @return Response string of all tasks
     */
    public String showSortedList(ArrayList<Task> list) {
        StringBuilder response = new StringBuilder("Here stand thy tasks, ranked by noble priority:\n");
        list.stream().sorted((t1, t2) -> t1.getPriority().getPriorityLevel() - t2.getPriority().getPriorityLevel())
                .forEach(task ->
                        response.append(list.indexOf(task) + 1)
                                .append(". ")
                                .append(task)
                                .append("\n"));
        return response.toString();
    }

    /**
     * Shows specific tasks matching find command
     *
     * @param list     List of all tasks
     * @param searches Search strings for tasks
     */
    public String showFindList(ArrayList<Task> list, String... searches) {
        StringBuilder response = new StringBuilder("Lo! These tasks dost match thy search:\n");
        list.stream()
                .filter(task ->
                        java.util.Arrays.stream(searches)
                                .anyMatch(search -> task.getDescription().contains(search)))
                .forEach(task ->
                        response.append(list.indexOf(task) + 1)
                                .append(". ")
                                .append(task)
                                .append("\n"));
        return response.toString();
    }


    /**
     * Show specific task created for todo / deadline / event command
     *
     * @param task Task to be created
     * @param size Updated size of task list
     */
    public String showTaskCreate(Task task, int size) {
        String response = "It is decreed! I have added this task to thy realm:\n";
        response = response.concat(task.toString() + "\n");
        response = response.concat("Thou now hold " + size + " tasks in thy service.");
        return response;
    }

    /**
     * Show specific task marked for mark command
     *
     * @param task Task marked
     */
    public String showMark(Task task) {
        String response = "Well done! This task is henceforth marked as complete by royal decree:\n";
        response = response.concat(task.toString());
        return response;
    }

    /**
     * Show specific task unmarked for unmark command
     *
     * @param task Task unmarked
     */
    public String showUnmark(Task task) {
        String response = "Alas! I have returned this task to its incomplete state:\n";
        response = response.concat(task.toString());
        return response;
    }

    /**
     * Show specific task deleted for delete command
     *
     * @param task Task deleted
     * @param size Updated size of task list
     */
    public String showDelete(Task task, int size) {
        String response = "By my command, this task is struck from the royal record:\n";
        response = response.concat(task.toString()) + "\n";
        response = response.concat("Thou now hold " + size + " tasks in thy service.");
        return response;
    }

    /**
     * Show confirmation for clearing all tasks
     *
     * @param previousSize number of tasks removed
     * @return confirmation message
     */
    public String showClear(int previousSize) {
        if (previousSize == 0) {
            return "Thy ledger is already barren, subject.";
        }
        return "By my will, all " + previousSize + " tasks have been swept clean from thy list.";
    }

    /**
     * Show bye message for bye command
     */
    public String showBye() {
        return "Fare thee well! Withdraw now from mine court, and may we meet anon!";
    }

    /**
     * Show message for invalid command
     */
    public String showInvalidCommand() {
        return "Error! That command is not recognized in mine kingdom.";
    }

    /**
     * Show message for invalid task
     *
     * @return String error message
     */
    public String showInvalidTask() {
        return "Error! Such a task existeth not in thy service.";
    }

    /**
     * Show message for invalid date time
     *
     * @return String error message
     */
    public String showInvalidDateTime() {
        return "Error! Thy date is ill-formed. Present it as YYYY-MM-DD, lest chaos reign.";
    }

    /**
     * Show message for KingException
     *
     * @param e Exception given for error in user IO
     * @return String error message
     */
    public String showError(KingException e) {
        return e.getMessage();
    }
}
