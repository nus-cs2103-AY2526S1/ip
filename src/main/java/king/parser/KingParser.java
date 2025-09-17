package king.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import king.KingException;
import king.task.Task;

/**
 * Parser for the King that helps with checking which commands have been called based on user input
 */
public class KingParser {
    /**
     * Enumeration of all possible commands for King bot
     */
    public enum Commands {
        HELP,
        LIST,
        SORTED_LIST,
        DUE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        CLEAR,
        BYE
    }

    private final String helpRegex = "^help$";
    private final String listRegex = "^list(?:\\s+(/sorted))?$";
    private final String dueRegex = "^due(?:\\s+(.*))?$";
    private final String findRegex = "^find(?:\\s+(.*))?$";
    private final String todoRegex = "^todo(?:\\s+(.*?)\\s*(?:/priority\\s+(.+))?)?$";
    private final String deadlineRegex = "^deadline(?:\\s+(.*?)\\s*"
            + "(?:/priority\\s+(.+?))?\\s*"
            + "(?:/by\\s+(.+))?)?$";
    private final String eventRegex = "^event(?:\\s+(.*?)\\s*"
            + "(?:/priority\\s+(.+?))?\\s*"
            + "(?:/from\\s+(.+?))?\\s*"
            + "(?:/to\\s+(.+?))?)?$";


    private final String markRegex = "^mark(?:\\s+(\\d*))?$";
    private final String unmarkRegex = "^unmark(?:\\s+(\\d*))?$";
    private final String deleteRegex = "^delete(?:\\s+(\\d*))?$";
    private final String clearRegex = "^clear$";
    private final String endRegex = "^bye$";
    private Matcher helpMatcher;
    private Matcher listMatcher;
    private Matcher dueMatcher;
    private Matcher findMatcher;
    private Matcher todoMatcher;
    private Matcher deadlineMatcher;
    private Matcher eventMatcher;
    private Matcher markMatcher;
    private Matcher unmarkMatcher;
    private Matcher deleteMatcher;
    private Matcher clearMatcher;
    private Matcher endMatcher;

    private String input;

    /**
     * Instantiates the parser with empty string input
     */
    public KingParser() {
        this("");
    }

    /**
     * Instantiates the parser with a string input that the parser uses to check with the matchers
     *
     * @param input String input for parser to check
     */
    public KingParser(String input) {
        setNewInput(input);
    }

    /**
     * Sets the input for the parser object by recompiling the matchers to check with text
     *
     * @param input String input for parser to check
     */
    public void setNewInput(String input) {
        input = input.strip();
        this.input = input;
        helpMatcher = Pattern.compile(helpRegex).matcher(input);
        listMatcher = Pattern.compile(listRegex).matcher(input);
        dueMatcher = Pattern.compile(dueRegex).matcher(input);
        findMatcher = Pattern.compile(findRegex).matcher(input);
        todoMatcher = Pattern.compile(todoRegex).matcher(input);
        deadlineMatcher = Pattern.compile(deadlineRegex).matcher(input);
        eventMatcher = Pattern.compile(eventRegex).matcher(input);
        markMatcher = Pattern.compile(markRegex).matcher(input);
        unmarkMatcher = Pattern.compile(unmarkRegex).matcher(input);
        deleteMatcher = Pattern.compile(deleteRegex).matcher(input);
        clearMatcher = Pattern.compile(clearRegex).matcher(input);
        endMatcher = Pattern.compile(endRegex).matcher(input);
    }

    /**
     * Check if a specific command has been called based on the matchers
     *
     * @param command King command to check for
     * @return If command has been called, return true.
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    public boolean checkParser(Commands command) throws KingException {
        switch (command) {
        case HELP:
            return helpMatcher.matches();
        case LIST:
            return listMatcher.matches();
        case DUE:
            return checkDue();
        case FIND:
            return checkFind();
        case TODO:
            return checkTodo();
        case DEADLINE:
            return checkDeadline();
        case EVENT:
            return checkEvent();
        case MARK:
            return checkMark();
        case UNMARK:
            return checkUnmark();
        case DELETE:
            return checkDelete();
        case CLEAR:
            return clearMatcher.matches();
        case BYE:
            return endMatcher.matches();
        default:
            return false;
        }
    }

    /**
     * Checks if command matches due command or missing deadline
     *
     * @return True if match due command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkDue() throws KingException {
        if (!dueMatcher.matches()) {
            return false;
        } else if (dueMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.DEADLINE_MISSING_DEADLINE);
        }
        return true;
    }

    /**
     * Checks if command matches find command or missing search text
     *
     * @return True if match find command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkFind() throws KingException {
        if (!findMatcher.matches()) {
            return false;
        } else if (findMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.FIND_MISSING_SEARCH);
        }
        return true;
    }

    /**
     * Checks if command matches todo command or missing description and priority
     *
     * @return True if match todo command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkTodo() throws KingException {
        if (!todoMatcher.matches()) {
            return false;
        }
        if (todoMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_DESCRIPTION);
        } else if (todoMatcher.group(2) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_PRIORITY);
        } else if (!checkValidPriority(todoMatcher.group(2))) {
            throw new KingException(KingException.ErrorMessage.INCORRECT_TASK_PRIORITY);
        }

        return true;
    }

    /**
     * Checks if command matches deadline command or missing description, priority or deadline
     *
     * @return True if match deadline command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkDeadline() throws KingException {
        if (!deadlineMatcher.matches()) {
            return false;
        }
        if (deadlineMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_DESCRIPTION);
        } else if (deadlineMatcher.group(2) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_PRIORITY);
        } else if (!checkValidPriority(deadlineMatcher.group(2))) {
            throw new KingException(KingException.ErrorMessage.INCORRECT_TASK_PRIORITY);
        } else if (deadlineMatcher.group(3) == null) {
            throw new KingException(KingException.ErrorMessage.DEADLINE_MISSING_DEADLINE);
        }
        return true;
    }

    /**
     * Checks if command matches event command or missing description, priority and from / to dates
     *
     * @return True if match event command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkEvent() throws KingException {
        if (!eventMatcher.matches()) {
            return false;
        }
        if (eventMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_DESCRIPTION);
        } else if (eventMatcher.group(2) == null) {
            throw new KingException(KingException.ErrorMessage.MISSING_TASK_PRIORITY);
        } else if (!checkValidPriority(eventMatcher.group(2))) {
            throw new KingException(KingException.ErrorMessage.INCORRECT_TASK_PRIORITY);
        } else if (eventMatcher.group(3) == null && eventMatcher.group(4) == null) {
            throw new KingException(KingException.ErrorMessage.EVENT_MISSING_FROM_TO_DATE);
        } else if (eventMatcher.group(3) == null) {
            throw new KingException(KingException.ErrorMessage.EVENT_MISSING_FROM_DATE);
        } else if (eventMatcher.group(4) == null) {
            throw new KingException(KingException.ErrorMessage.EVENT_MISSING_TO_DATE);
        }
        return true;
    }

    /**
     * Checks if command matches mark command or missing index
     *
     * @return True if match mark command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkMark() throws KingException {
        if (!markMatcher.matches()) {
            return false;
        }
        if (markMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.MARK_MISSING_INDEX);
        }
        return true;
    }

    /**
     * Checks if command matches unmark command or missing index
     *
     * @return True if match unmark command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkUnmark() throws KingException {
        if (!unmarkMatcher.matches()) {
            return false;
        }
        if (unmarkMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.UNMARK_MISSING_INDEX);
        }
        return true;
    }

    /**
     * Checks if command matches delete command or missing index
     *
     * @return True if match delete command
     * @throws KingException If matching group is missing certain parts, throw IOError KingException.
     */
    private boolean checkDelete() throws KingException {
        if (!deleteMatcher.matches()) {
            return false;
        }
        if (deleteMatcher.group(1) == null) {
            throw new KingException(KingException.ErrorMessage.DELETE_MISSING_INDEX);
        }
        return true;
    }

    /**
     * Checks if the string provided is a valid priority text
     *
     * @param priorityText Input text
     * @return True if priority is given correctly, else false.
     */
    private boolean checkValidPriority(String priorityText) {
        for (Task.Priority p : Task.Priority.values()) {
            if (p.getDatabaseText().equals(priorityText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets help command matcher
     *
     * @return Help command matcher
     */
    public Matcher getHelpMatcher() {
        return helpMatcher;
    }

    /**
     * Gets list command matcher
     *
     * @return List command matcher
     */
    public Matcher getListMatcher() {
        return listMatcher;
    }

    /**
     * Gets due command matcher
     *
     * @return Due command matcher
     */
    public Matcher getDueMatcher() {
        return dueMatcher;
    }

    /**
     * Gets find command matcher
     *
     * @return Find command matcher
     */
    public Matcher getFindMatcher() {
        return findMatcher;
    }

    /**
     * Gets todo command matcher
     *
     * @return Todo command matcher
     */
    public Matcher getTodoMatcher() {
        return todoMatcher;
    }

    /**
     * Gets deadline command matcher
     *
     * @return Deadline command matcher
     */
    public Matcher getDeadlineMatcher() {
        return deadlineMatcher;
    }

    /**
     * Gets event commmand matcher
     *
     * @return Event command matcher
     */
    public Matcher getEventMatcher() {
        return eventMatcher;
    }

    /**
     * Gets mark command matcher
     *
     * @return Mark command matcher
     */
    public Matcher getMarkMatcher() {
        return markMatcher;
    }

    /**
     * Gets unmark command matcher
     *
     * @return Unmark command matcher
     */
    public Matcher getUnmarkMatcher() {
        return unmarkMatcher;
    }

    /**
     * Gets delete command matcher
     *
     * @return Delete command matcher
     */
    public Matcher getDeleteMatcher() {
        return deleteMatcher;
    }

    /**
     * Gets clear command matcher
     *
     * @return Clear command matcher
     */
    public Matcher getClearMatcher() {
        return clearMatcher;
    }

    /**
     * Gets end command matcher
     *
     * @return End command matcher
     */
    public Matcher getEndMatcher() {
        return endMatcher;
    }
}
