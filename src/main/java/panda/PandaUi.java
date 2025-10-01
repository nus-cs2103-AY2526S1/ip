package panda;

import java.util.Scanner;

import panda.task.Task;

/**
 * PandaUi handles all interactions with the user, apart from
 * exception messages that arise from user input that are formatted wrongly.
 * */
public class PandaUi {
    private static final String NAME = "Panda";
    private static final String LINES = "_".repeat(90) + "\n";
    private static final String GREETING = "Hello I'm " + NAME + " your cute personal assistant!\n"
            + "What can I do for you?\n" + "If you are unsure, just type \"help\"!";
    private static final String LIST = "Here are your tasks:";
    private static final String KEYWORD_LIST = "Found your matching tasks:";
    private static final String ADDED_TASK = "Got it. I've added this task: \n  ";
    private static final String BYE = "Give me some bamboo before you leave!";
    private static final String MARKED_TASK = "Goob job, this task is completed:\n  ";
    private static final String UNMARKED_TASK = "Don't forget to complete this task:\n  ";
    private static final String DELETED_TASK = "Goodbye to this task:\n  ";
    private static final String LOAD_ERROR = "IO Error: Invalid data format in file, data will not be loaded!";
    private static final String SAVE_ERROR = "IO Error: Task data could not be saved to panda.txt.";
    private static final String HELP_INFO = """
            Let me help you! Here are the list of actions that are available and how to use them:
            1. todo (description) - Creates a todo task
            2. deadline (description) /by (deadline dateTime) - Creates a deadline task
            3. event (description) /from (start dateTime) /to (end dateTime) - Creates an event task
            4. list - Shows all the tasks added
            5. mark (task no.) - Changes the status of the specified task to done
            6. unmark (task no.) - Changes the status of the specified task to not done
            7. delete (task no.) - Removes the specified task
            8. find (keyword) - Shows all tasks that contain the keyword
            9. bye - Exits the program
            """;

    private final Scanner scanner;
    private String response;

    /**
     * Constructs PandaUi object.
     */
    public PandaUi() {
        scanner = new Scanner(System.in);
        response = "";
    }

    /**
     * Supports generation of output for both text-based UI and the GUI.
     *
     * @param message Message to be displayed to user.
     */
    public void reply(String message) {
        response = message;
        assert !response.isEmpty() : "Empty response to user!";
        System.out.println(LINES + response + "\n" + LINES);
    }

    public String provideResponse() {
        return response;
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void greet() {
        reply(GREETING);
    }

    public void list(TaskList tasks) {
        reply(LIST + tasks);
    }

    public void listKeywordTasks(TaskList tasks, String keyword) {
        reply(KEYWORD_LIST + tasks.generateListWithKeywords(keyword));
    }

    private String showTasksSize(TaskList tasks) {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        reply(ADDED_TASK + task + showTasksSize(tasks));
    }

    public void showTaskDeleted(Task task, TaskList tasks) {
        reply(DELETED_TASK + task + showTasksSize(tasks));
    }

    public void bye() {
        reply(BYE);
    }

    public void showTaskMarked(Task task) {
        reply(MARKED_TASK + task);
    }

    public void showTaskUnmarked(Task task) {
        reply(UNMARKED_TASK + task);
    }

    public void showLoadError() {
        reply(LOAD_ERROR);
    }

    public void showSaveError() {
        reply(SAVE_ERROR);
    }

    public void showPandaError(PandaException e) {
        reply(e.getMessage());
    }

    public void showHelp() {
        reply(HELP_INFO);
    }
}
