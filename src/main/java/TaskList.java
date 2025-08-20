public class TaskList {
    private Task[] tasks;
    private int count;

    public TaskList(int length) {
        this.count = 0;
        this.tasks = new Task[length];
    }

    //addition of tasks
    public void addTask(Task task) {
        tasks[count] = task;
        count++;
        System.out.println("############################################################");
        System.out.println("Got it. I have added this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + count + " tasks in the list");
        System.out.println("############################################################");
    }

    //list all task out
    public void listTasks() {
        System.out.println("############################################################");
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("############################################################");
    }

    //marking the task
    public void markTask(int index) {
        Task task = tasks[index - 1];
        task.markAsDone();
        System.out.println("############################################################");
        System.out.println("Good Job! I have marked this task as done: ");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    //unmarking the task
    public void unmarkTask(int index) {
        Task task = tasks[index - 1];
        task.unmarkDone();
        System.out.println("############################################################");
        System.out.println("Oh no! I have unmarked this task for now: ");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    public int getCount() {
        return this.count;
    }
}
