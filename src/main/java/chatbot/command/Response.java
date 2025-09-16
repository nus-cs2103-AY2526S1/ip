package chatbot.command;
/**
 * Represents the possible response messages used by the Harry chatbot.
 * Each {@code Response} constant corresponds to a specific outcome
 * of a user command, such as success, failure, or status updates.
 */
public enum Response {
    MARK_SUCCESS("Nice! I've marked this task as done:\n"),
    UNMARK_SUCCESS("OK, I've marked this task as not done yet:\n"),
    TASK_SUCCESS("Got it. I've added this task:"),
    NUMBER_FAILURE("I need a number index"),
    MARK_FAILURE("There's nothing to mark!"),
    UNMARK_FAILURE("There's nothing to unmark!"),
    DELETE_FAILURE("There's nothing to delete!"),
    LIST_FAILURE("There's nothing to list!"),
    LIST_TASKS("Here are the tasks in your list:"),
    REMOVE_TASK("Noted. I've removed this task:\n"),
    SNOOZE_SUCCESS("Sure! I've snoozed this task:\n"),
    SNOOZE_FAILURE("There's nothing to snooze!");

    private final String message;
    Response(String message) {
        this.message = message;
    }

    /**
     * Returns the string message tied to this response
     *
     * This method returns the string message that is defined as the message's response
     * Use this method like this Response.NUMBER_FAILURE.getMessage()
     *
     * @return the String representing message
     */
    public String getMessage() {
        return this.message;
    }
}
