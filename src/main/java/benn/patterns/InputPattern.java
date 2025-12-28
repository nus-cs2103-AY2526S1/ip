package benn.patterns;

import java.util.regex.Pattern;

/**
 * Defines the regular expression patterns used to parse user commands
 * in Benn the Chatbot.
 *
 * <p>Each {@code Pattern} corresponds to a specific command keyword,
 * and is used by the {@link benn.parser.Parser} to recognize valid input
 * and extract relevant arguments such as task descriptions, indices,
 * and date/time values.</p>
 *
 * <p>The supported commands are:</p>
 * <ul>
 *   <li><b>list</b> — {@link #LIST}</li>
 *   <li><b>todo</b> — {@link #ADD_TODO}</li>
 *   <li><b>deadline</b> — {@link #ADD_DEADLINE}</li>
 *   <li><b>event</b> — {@link #ADD_EVENT}</li>
 *   <li><b>mark</b> — {@link #MARK_TASK}</li>
 *   <li><b>unmark</b> — {@link #UNMARK_TASK}</li>
 *   <li><b>delete</b> — {@link #DELETE_TASK}</li>
 *   <li><b>bye</b> — {@link #BYE}</li>
 * </ul>
 *
 * <p>Date and time values are validated against strict formats:
 * <ul>
 *   <li>Date: {@code dd/MM/yyyy}</li>
 *   <li>Time: {@code HH:mm} (24-hour)</li>
 *   <li>Datetime: {@code dd/MM/yyyy HH:mm}</li>
 * </ul>
 * For example: {@code 12/12/2069 14:50}</p>
 */
public final class InputPattern {
    private static final String DATE = "(?:0[1-9]|[12][0-9]|3[01])/(?:0[1-9]|1[0-2])/\\d{4}";
    private static final String TIME = "(?:[01]\\d|2[0-3]):[0-5]\\d";
    private static final String DATETIME = DATE + "\\s+" + TIME;

    public static final Pattern LIST = Pattern.compile("(?i)^\\s*list\\s*$");
    public static final Pattern ADD_TODO = Pattern.compile("(?i)^\\s*todo\\s+(?<description>.+)\\s*$");
    public static final Pattern ADD_DEADLINE = Pattern.compile("(?i)^\\s*deadline\\s+(?<description>.+?)\\s*/by\\s+(?<dateTimeDue>" + DATETIME + ")\\s*$");
    public static final Pattern ADD_EVENT = Pattern.compile("(?i)^\\s*event\\s+(?<description>.+?)\\s*/from\\s+(?<startDateTime>" + DATETIME + ")\\s*/to\\s+(?<endDateTime>" + DATETIME + ")\\s*$");
    public static final Pattern MARK_TASK = Pattern.compile("(?i)^\\s*mark\\s+(?<index>\\d+)\\s*$");
    public static final Pattern UNMARK_TASK = Pattern.compile("(?i)^\\s*unmark\\s+(?<index>\\d+)\\s*$");
    public static final Pattern DELETE_TASK = Pattern.compile("(?i)^\\s*delete\\s+(?<index>\\d+)\\s*$");
    public static final Pattern FIND_ALL_TASKS_CONTAINING_KEYWORD = Pattern.compile("(?i)^\\s*find\\s+(?<keyword>(?!\\s*$).+)\\s*$");
    public static final Pattern HELP = Pattern.compile("^help$");
    public static final Pattern BYE = Pattern.compile("(?i)^\\s*bye\\s*$");
}


