import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList(int length) {
        this.tasks = new ArrayList<>();
    }

    //addition of tasks
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("############################################################");
        System.out.println("Got it. I have added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println("############################################################");
    }

    //list all task out
    public void listTasks() {
        System.out.println("############################################################");
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("############################################################");
    }

    //marking the task
    public void markTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("############################################################");
        System.out.println("Good Job! I have marked this task as done:");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    //unmarking the task
    public void unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.unmarkDone();
        System.out.println("############################################################");
        System.out.println("Oh no! I have unmarked this task for now:");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    public void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        System.out.println("############################################################");
        System.out.println("Okay! I will remove this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now there is " + (tasks.size() - 1) + " tasks in the list!");
        System.out.println("############################################################");
        tasks.remove(index - 1);
    }

    public int getCount() {
        return tasks.size();
    }
}
