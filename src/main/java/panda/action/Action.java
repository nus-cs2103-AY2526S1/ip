package panda.action;

import panda.FileManager;
import panda.PandaException;
import panda.PandaUi;
import panda.TaskList;

/**
 * Action is the superclass for AddAction, DeleteAction, ExitAction,
 * ListAction and MarkAction.
 */
public abstract class Action {
    public abstract void execute(TaskList tasks, PandaUi ui, FileManager fileManager) throws PandaException;

    public abstract boolean isExit();
}
