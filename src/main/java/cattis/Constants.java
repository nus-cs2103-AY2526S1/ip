package cattis;

/**
 * A collection of all global constants for {@code Cattis} Application
 * It is important to put all relevant string literals in this
 * class, except error messages which are handled in {@code CattisException}.
 */
public final class Constants {
    public static final String CHATBOT_NAME = "CATTIS \uD83D\uDC31";
    public static final String GREETING_MSG = String.format(
            "Hello! I'm %s.\nWhat can I do for you?", CHATBOT_NAME);
    public static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    public static final String OPEN_CALENDAR = "Opening calendar view\n";
    public static final String LIST_TASK_MSG = "Here are the tasks in your list:";
    public static final String MARK_TASK_MSG = "Nice! I've marked this task as done:\n%s\n";
    public static final String UNMARK_TASK_MSG = "OK, I've marked this task as not done yet:\n%s\n";
    public static final String ADD_TASK_MSG = "Got it. I've added this task:\n%s\n";
    public static final String REMOVE_TASK_MSG = "Noted. I've removed this task:\n%s\n";
    public static final String SUMMARY_TASK_MSG = "Now you have %s tasks in the list.\n";
    public static final String EMPTY_MSG = "<empty>";
}
