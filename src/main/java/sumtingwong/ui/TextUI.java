package sumtingwong.ui;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Console-based user interface for displaying messages and interacting
 * with the {@link TaskList} during the application's lifecycle.
 */
public class TextUI {
    private static final String DIVIDER = "------------------------------- \n";
    private static final String BOT_NAME = "SumTingWong";
    protected static TaskList taskList;

    private final Scanner scanner;
    private final Consumer<String> printer;
    private final boolean useDividers;

    /**
     * Creates a UI bound to the provided task list.
     *
     * @param taskList the task list to display and mutate
     */
    public TextUI(TaskList taskList) {
        this(taskList, System.out::println, true);
    }

    /**
     * Creates a UI with a custom printer. For GUI, supply a printer that buffers output.
     *
     * @param taskList the task list to display and mutate
     * @param printer function to handle output (e.g., System.out::println for console, StringBuilder::append for GUI)
     */
    public TextUI(TaskList taskList, Consumer<String> printer) {
        this(taskList, printer, true);
    }

    /**
     * Creates a UI with a custom printer and divider mode. For GUI, set useDividers to false.
     *
     * @param taskList the task list to display and mutate
     * @param printer function to handle output (e.g., System.out::println for console, StringBuilder::append for GUI)
     * @param useDividers whether to include dividers in output (false for GUI)
     */
    public TextUI(TaskList taskList, Consumer<String> printer, boolean useDividers) {
        assert taskList != null : "TaskList cannot be null";
        assert printer != null : "Printer function cannot be null";
        
        this.scanner = new Scanner(System.in);
        TextUI.taskList = taskList;
        this.printer = printer;
        this.useDividers = useDividers;
    }

    /**
     * Returns the divider string used in console output.
     *
     * @return divider string
     */
    public static String getDivider() {
        return DIVIDER;
    }

    /**
     * Prints the full list of tasks to the console.
     */
    public void showListMessage() {
        String message = "Ugh, fine. Here your damn task list: \n"
                + taskList.toString()
                + "\nThere, happy now? -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Prints the goodbye message.
     */
    public void showByeMessage() {
        String message = "Finally! Go away and don't bother me again. I got better things to do dan babysit your tasks. >:(";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Reads a trimmed line of input from the console.
     *
     * @return user input without leading/trailing spaces
     */
    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints an error message.
     *
     * @param message the error to display
     */
    public void showError(String message) {
        printer.accept("What... " + message + " Seriously? Cannot even type properly? -.-");
    }

    /**
     * Marks a task as not done and prints feedback.
     *
     * @param listIndex zero-based index of the task
     */
    public void showUnMarkMessage(int listIndex) {
        assert listIndex >= 0 : "List index must be non-negative";
        assert listIndex < taskList.size() : "List index must be within bounds";
        
        String message = "Tch, fine. I unmarked dis task for you: \n"
                + taskList.get(listIndex).toString()
                + "\nGuess you not so productive as you tought, huh? -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Marks a task as done and prints feedback.
     *
     * @param listIndex zero-based index of the task
     */
    public void showMarkMessage(int listIndex) {
        assert listIndex >= 0 : "List index must be non-negative";
        assert listIndex < taskList.size() : "List index must be within bounds";
        
        String message = "Hmph, about time. I marked dis task as done: \n"
                + taskList.get(listIndex).toString()
                + "\nDon't get too excited, you still got " + (taskList.size() - 1) + " more to go. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Adds a deadline task to the list and prints feedback.
     *
     * @param deadline the task to add
     */
    public void showDeadlineMessage(Task deadline) {
        assert deadline != null : "Deadline task cannot be null";
        assert deadline instanceof Deadline : "Task must be a Deadline instance";
        
        String message = "Ugh, anoder deadline? Fine, I added dis task: \n    "
                + deadline.toString()
                + "\nNow you have "
                + taskList.size()
                + " tasks cluttering up your list. Better don't procrastinate dis time. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Adds a todo task to the list and prints feedback.
     *
     * @param todo the task to add
     */
    public void showToDoMessage(Task todo) {
        assert todo != null : "Todo task cannot be null";
        assert todo instanceof ToDo : "Task must be a ToDo instance";
        
        String message = "Tch, whatever. I added dis task: \n    "
                + todo.toString()
                + "\nNow you have "
                + taskList.size()
                + " tasks in your list. Don't expect me to remind you about dem. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Adds an event task to the list and prints feedback.
     *
     * @param event the task to add
     */
    public void showEventMessage(Task event) {
        assert event != null : "Event task cannot be null";
        assert event instanceof Event : "Task must be an Event instance";
        
        String message = "Hmph, anoder event? Fine, I added dis task: \n    "
                + event.toString()
                + "\nNow you have "
                + taskList.size()
                + " tasks in your list. Hope you don't forget about dis one too. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Deletes the task at the given index and prints feedback.
     *
     * @param deletedTask the Task object that has been deleted
     */
    public void showDeleteMessage(Task deletedTask) {
        assert deletedTask != null : "Deleted task cannot be null";
        
        String message = "Tch, giving up already? Fine, I removed dis task: \n    "
                + deletedTask.toString()
                + "\nNow you have "
                + taskList.size()
                + " tasks left. At least you being realistic about your capabilities. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Displays tasks that match a keyword search.
     *
     * @param tasks string representation of the tasks that match the keyword
     */
    public void showFindMessage(String tasks) {
        assert tasks != null : "Tasks string cannot be null";
        
        String message = "Ugh, fine. Here are the tasks dat match your search: \n"
                + tasks
                + "\nThere, found dem for you. Don't say I never do anyting nice. -.-";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Shows a message when tags are added to a task.
     *
     * @param task the task that was tagged
     * @param tagsString the tags that were added
     */
    public void showTagAddedMessage(Task task, String tagsString) {
        assert task != null : "Task cannot be null";
        assert tagsString != null : "Tags string cannot be null";
        
        String message = "Got it! I added dis tags to de task:\n    "
                + task.toString()
                + "\nAdded tags: " + tagsString;
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Shows a message when tags are removed from a task.
     *
     * @param task the task that was untagged
     * @param tagsString the tags that were removed
     */
    public void showTagRemovedMessage(Task task, String tagsString) {
        assert task != null : "Task cannot be null";
        assert tagsString != null : "Tags string cannot be null";
        
        String message = "Got it! I removed dis tags from de task:\n    "
                + task.toString()
                + "\nRemoved tags: " + tagsString;
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Shows filtered tasks by tags.
     *
     * @param tasks string representation of the filtered tasks
     * @param tags the tags that were used for filtering
     */
    public void showFilteredTasksMessage(String tasks, String tags) {
        assert tasks != null : "Tasks string cannot be null";
        assert tags != null : "Tags string cannot be null";
        
        String message;
        if (tasks.trim().isEmpty()) {
            message = "No tasks found wit tags: " + tags;
        } else {
            message = "Here are the tasks wit tags (" + tags + "):\n" + tasks;
        }
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Shows a greeting message for casual input like "hi", "hello", etc.
     */
    public void showGreetingMessage() {
        String message = "Oh, you saying hi? Well, hi back to you too. Now what you want me to do?";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }

    /**
     * Shows a message for unknown commands.
     *
     * @param command the unknown command that was entered
     */
    public void showUnknownCommandMessage(String command) {
        String message = "Huh? What is '" + command + "'? I don't know dat command. Try 'list' to see what I can do.";
        
        if (useDividers) {
            printer.accept(DIVIDER + message + DIVIDER);
        } else {
            printer.accept(message);
        }
    }
}