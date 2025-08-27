package zell;

/**
 * Represents an Enum which contains all the default Zell messages
 */
public enum ZellMessage {
    WELCOME_MESSAGE("Hello! My name is Zell\n How can I help you?"),
    UNKNOWN_COMMAND(" is an unknown command. Please try again with a valid command"),
    TASK_ADDED("Noted. The following task has been added:\n "),
    TASK_REMOVED("Noted. The following task has been removed:\n "),
    GOODBYE("Goodbye. Hope to see you again soon!"),
    LIST("Currently you have added this tasks to your list:\n"),
    TASK_MARKED("Nice! I've marked this task as done:\n "),
    TASK_UNMARKED("OK, I've marked this task as not done yet:\n "),
    TASK_FOUND("Here are the matching tasks in your list:\n");

    /** The default message for the scenario  */
    private String message;

    ZellMessage(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
