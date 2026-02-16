package taro.command;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.ui.Ui;

/**
 * An interface to identify different types of command
 */
public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException;
}
