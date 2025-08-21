package taskTypes;

public class taskList {
    protected Task[] listOfTasks;
    private static int taskCount;

    public taskList() {
        listOfTasks = new Task[100];
        taskCount = 1;
    }

    public Task[] getTaskList() {
        return listOfTasks;
    }

    public void addToList(Task task) {
        listOfTasks[taskCount++] = task;
    }

    public Task getTask(int index) {
        return listOfTasks[index];
    }

    public int getTaskCount() {
        return taskCount;
    }
}
