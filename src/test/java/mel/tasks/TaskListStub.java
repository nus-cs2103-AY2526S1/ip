package mel.tasks;

import mel.tasks.TaskList;

public class TaskListStub extends TaskList {

    public TaskListStub() {
        super(null);

    }

    @Override
    public String add(Task task) {
        return task.toString();

    }


}
