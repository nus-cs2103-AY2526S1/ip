package moon.ui;

import java.util.List;

import moon.models.Task;
import moon.models.TaskList;
import moon.models.TaskMatch;

/**
 * Centralized message templates used by the GUI and logic.
 * <p>
 * All methods are stateless and return a formatted string ready for display.
 */
public final class UiMessages {

    private UiMessages() { /* no instances */ }

    /** @return the greeting banner/message shown at startup */
    public static String showGreetingMessage() {
        String logo = """
                 __  __   ____    ____    _   _
                |  \\/  | / __ \\  / __ \\  | \\ | |     __      _
                | |\\/| || |  | || | () | |  \\| |   o'')}____//
                | |  | || |__| || @ . o| | |\\  |    `_/      )
                |_|  |_| \\____/  \\____/  |_| \\_|    (_(_/-(_/
                """;
        // The logo is not included because it renders terrible in the GUI:
        return "Meooowww! I'm Moon! Your personal assistant!";
    }

    /** @return the farewell message before exiting */
    public static String showExitMessage() {
        return "Byeee! Hope to see you again! Meooowww!";
    }

    /** @return the generic prompt asking for the next command */
    public static String showAskingMessage() {
        return "How may I help you?";
    }

    /** @return a formatted exception message to display to the user */
    public static String showExceptionMessage(String s) {
        return s;
    }

    /** @return a generic error message for unexpected I/O or system errors */
    public static String showGeneralErrorMessage() {
        return "Sorryyy! I incurred some error while trying to add this task.\nWould you mind trying again? Meow!";
    }

    /** @return a message indicating that a previous task list was loaded successfully */
    public static String showLoadStorageSuccessfulMessage(TaskList list) {
        return String.format("I have retrieved your previous task list!\n%s  Meow!", list);
    }

    /** @return a message indicating that storage could not be loaded */
    public static String showLoadStorageUnsuccessfulMessage() {
        return "I couldn't retrieve your previous task. No worries! Let's start again! Meow!";
    }

    /** @return a message indicating that storage was empty on first run */
    public static String showEmptyInitialStorageMessage() {
        return "Time to start tasking! Meooowww!";
    }

    /** @return a confirmation message for adding a task */
    public static String showAddTaskMessage(Task addedTask) {
        return String.format("Copy that! I've added this task!\n\t%s\n", addedTask);
    }

    /** @return a confirmation message for deleting a task */
    public static String showDeleteTaskMessage(Task deletedTask) {
        return String.format("Copy that! I've deleted this task! Meow!\n\t%s\n", deletedTask);
    }

    /** @return a formatted list message (or encouragement when empty) */
    public static String showListMessage(TaskList list) {
        if (list.isEmpty()) {
            return "You haven't added anything to your list yet. Time to start tasking! Meooowww!";
        } else {
            return String.format("Here are the items in your list!\n%s  Meow!\n", list);
        }
    }

    /** @return a message for trying to mark an already-done task */
    public static String showAlreadyMarkedMessage(Task alreadyMarkedTask) {
        return String.format("I see you have already marked this task!\n  %s\n", alreadyMarkedTask);
    }

    /** @return a message for trying to unmark an already-not-done task */
    public static String showAlreadyUnmarkedMessage(Task alreadyUnmarkedTask) {
        return String.format("I see you have already unmarked this task!\n  %s\n", alreadyUnmarkedTask);
    }

    /** @return a success message for marking a task done */
    public static String showMarkedSuccessfulMessage(Task markedTask) {
        return String.format("Nicely done! I've pawed this as done! Meow!\n\t  %s\n", markedTask);
    }

    /** @return a success message for unmarking a task */
    public static String showUnmarkedSuccessfulMessage(Task unmarkedTask) {
        return String.format("No worries! I've pawed this as not done! You can do it! Meow!\n  %s\n", unmarkedTask);
    }

    /** @return a message listing matched tasks for a keyword, or a miss message if empty */
    public static String showTasksMatchedMessage(List<TaskMatch> matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            return String.format("I can't find any tasks matching your keyword: %s . Meow:(\n", keyword);
        } else {
            return String.format("I found these tasks matching your keyword!\n%s\nMeow!\n",
                    TaskList.formatTaskMatchList(matchedTasks));
        }
    }
}
