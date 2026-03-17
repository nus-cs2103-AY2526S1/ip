package objectclasses.command;

import java.time.LocalDateTime;

import objectclasses.exception.LynxException;
import objectclasses.formatter.LynxDateManager;

/**
 * Represents a string of search modifiers for a reminder and stores its search results.
 * Does not handle the execution of commands.
 */
public class ReminderCommand extends LynxCommand {

    /**
     * Represents the two types of reminders.
     */
    private enum ReminderType {

        URGENT,
        INCOMPLETE;

    }

    private ReminderType type;

    private ReminderCommand(String input, ReminderType type) throws LynxException {
        super(input);
        this.type = type;
    }

    /**
     * Returns a <code>ReminderCommand</code> for urgent tasks today.
     *
     * @return <code>ReminderCommand</code> object.
     */
    public static ReminderCommand urgent() throws LynxException {
        LocalDateTime today = LocalDateTime.now();
        String searchString = String.format("/status incomplete /on %s", LynxDateManager.defaultDateTime(today));
        return new ReminderCommand(searchString, ReminderType.URGENT);
    }

    /**
     * Returns a <code>ReminderCommand</code> for incomplete tasks.
     *
     * @return <code>ReminderCommand</code> object.
     */
    public static ReminderCommand incomplete() throws LynxException {
        return new ReminderCommand("/status incomplete", ReminderType.INCOMPLETE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String emptyDialogue() {
        return "     (No tasks needing attention. Good job on completing tasks!)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String actionDialogue() {
        switch (type) {
        case URGENT -> {
            return "Take responsibility and complete your tasks:";
        }
        case INCOMPLETE -> {
            return "Start early. Begin working on these tasks now:";
        }
        default -> {
            return "";
        }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSearchString() {
        return actionDialogue();
    }

}
