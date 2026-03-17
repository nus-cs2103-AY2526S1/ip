package objectclasses.command;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import objectclasses.exception.CommandFormatException;
import objectclasses.exception.LynxException;
import objectclasses.task.Task;

/**
 * Represents a string of search modifiers and stores its search results.
 * Does not handle the execution of commands.
 */
public abstract class LynxCommand {

    private final String[] commands;
    private int index = 0;
    private String date = "";
    private String keyword = "";
    private String priority = "";
    private String id = "";
    private String status = "";
    private String type = "";
    private List<Task> searchResult = null;

    /**
     * Takes in a string containing search modifiers and parses it into separate components.
     *
     * @param input String of search modifiers.
     * @throws LynxException If input is of invalid format and cannot be parsed.
     */
    public LynxCommand(String input) throws LynxException {
        if (input.isBlank()) {
            throw CommandFormatException.emptyCommand();
        }
        try {
            commands = input.trim().split("\\s+");
        } catch (PatternSyntaxException e) {
            throw CommandFormatException.invalidCommandStructure();
        }
    }

    /**
     * Returns dialogue to be printed if no matching task has been found.
     *
     * @return Dialogue to be printed.
     */
    public abstract String emptyDialogue();

    /**
     * Returns dialogue representing the action performed on tasks.
     *
     * @return Dialogue to be printed.
     */
    public abstract String actionDialogue();

    /**
     * Stores a date as specified by a "/on" search modifier.
     * </p>
     * Date can only be set once.
     *
     * @param date String representation of a date.
     * @throws LynxException If more than one attempt to search by date is detected.
     */
    public void setDate(String date) throws LynxException {
        if (!this.date.isEmpty()) {
            throw CommandFormatException.multipleDate();
        }
        this.date = String.format(" occurring on %s", date);
    }

    /**
     * Stores a keyword as specified by a "/key" search modifier.
     *
     * @param keyword Keyword substring.
     */
    public void setKeyword(String keyword) {
        if (this.keyword.isEmpty()) {
            this.keyword = String.format(" containing keyword \"%s\"", keyword);
        } else {
            this.keyword = String.format("%s, \"%s\"", this.keyword, keyword);
        }
    }

    /**
     * Stores a priority as specified by a "/p" search modifier.
     *
     * @param priority Priority as a string.
     * @throws LynxException If more than one attempt to search by priority is detected.
     */
    public void setPriority(String priority) throws LynxException {
        if (!this.priority.isEmpty()) {
            throw CommandFormatException.multiplePriority();
        } else {
            this.priority = String.format(" of priority %s", priority);
        }
    }

    /**
     * Stores an id as specified by a "/id" search modifier.
     * </p>
     * Id can only be set once.
     *
     * @param id Id of a task.
     * @throws LynxException If more than one attempt to search by id is detected.
     */
    public void setId(String id) throws LynxException {
        if (!this.id.isEmpty()) {
            throw CommandFormatException.multipleId();
        }
        this.id = String.format(" with id %s", id);
    }

    /**
     * Stores a status as specified by a "/status" search modifier.
     *
     * @param status Status of a task.
     */
    public void setStatus(String status) {
        if (this.status.isEmpty()) {
            this.status = String.format(" %s", status);
        } else {
            this.status = String.format("%s, %s", this.status, status);
        }
    }

    /**
     * Stores a type as specified by a "/type" search modifier.
     *
     * @param type Type of task.
     */
    public void setType(String type) {
        if (this.type.isEmpty()) {
            this.type = String.format(" %s", type);
        } else {
            this.type = String.format("%s, %s", this.type, type);
        }
    }

    public void setSearchResult(List<Task> searchResult) {
        this.searchResult = searchResult;
    }

    public List<Task> getSearchResult() {
        assert (searchResult != null);
        return searchResult;
    }

    /**
     * Returns the next component amongst the search modifiers.
     *
     * @return Command component as a string.
     */
    public String getNextCommand() {
        assert (index >= 0);
        if (index >= commands.length) {
            return "";
        } else {
            String command = commands[index];
            index++;
            return command;
        }
    }

    /**
     * Returns a string that contains information on the search details.
     *
     * @return Dialogue of search details.
     */
    public String getSearchString() {
        String searchString = String.format("all%s%s tasks%s%s%s%s:", status, type, date, keyword, priority, id);
        if (searchString.length() > 100) {
            return "all matching tasks:";
        } else {
            return searchString;
        }
    }

}
