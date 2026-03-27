package command;

import amogus.AmogusException;
import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Represents a command interface for different
 * instructions to be executed by the chatbot.
 */
public interface Command {

    String execute(TaskList tasks, UI ui, FileStorage f) throws AmogusException;
    boolean isExit();
}
