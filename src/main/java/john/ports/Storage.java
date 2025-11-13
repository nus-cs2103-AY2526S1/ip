package john.ports;

import john.model.TaskList;

public interface Storage {
    TaskList load();
    void save(TaskList tasks);
}