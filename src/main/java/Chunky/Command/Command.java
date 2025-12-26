package Chunky.Command;

import Chunky.Storage.Storage;
import Chunky.Task.TaskList;

public interface Command {
    String execute(String input, TaskList tasks, Storage storage) throws Exception;
}

