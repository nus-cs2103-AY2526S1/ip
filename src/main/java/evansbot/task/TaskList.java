package evansbot.task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(Storage storage, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.storage = storage;
    }
    //addition of tasks
    public void addTask(Task task) {
        tasks.add(task);
        save();
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
        save();
        System.out.println("############################################################");
        System.out.println("Good Job! I have marked this task as done:");
        System.out.println(" " + task.toString());
        System.out.println("############################################################");
    }

    //unmarking the task
    public void unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.unmarkDone();
        save();
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
        save();
    }

    public int getCount() {
        return tasks.size();
    }

    private void save() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }
}
