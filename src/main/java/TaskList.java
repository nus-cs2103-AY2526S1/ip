public class TaskList {
    private String[] tasks;
    private int count;

    public TaskList(int length) {
        this.count = 0;
        this.tasks = new String[length];
    }

    //addition of tasks
    public void addTask(String task) {
        tasks[count] = task;
        count++;
        System.out.println("############################################################");
        System.out.println("added: " + task);
        System.out.println("############################################################");
    }

    //list all task out
    public void listTask() {
        System.out.println("############################################################");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("############################################################");
    }
}
