package woop.logic;

import woop.ui.Ui;

/**
 * Exception to handle when a task is already finished and user tries to mark it again.
 */
public class MarkedTaskException extends Exception {
    public MarkedTaskException(Task t) {
        super("Task is already marked:\n"
                + Ui.INDENT + t.toString());
    }
}
