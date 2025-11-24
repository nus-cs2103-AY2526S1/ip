package woop.logic;

import woop.ui.Ui;

/**
 * Exception to handle if user tries to unmark a unfinished task.
 */
public class UnmarkedTaskException extends Exception {
    public UnmarkedTaskException(Task t) {
        super("Task is already unmarked:\n"
                + Ui.INDENT + t.toString());
    }
}
