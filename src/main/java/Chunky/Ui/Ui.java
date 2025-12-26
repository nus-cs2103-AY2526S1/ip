package Chunky.Ui;

import Chunky.Task.Task;
import Chunky.Task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("Hello! I'm main.java.Chunky.Chunky.main.java.Chunky.Chunky!\n" + logo);
        System.out.println("What can I do for you ?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye! Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Okay! removed " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Okay! Unmarked the task:");
        System.out.println("  " + task);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints out the found tasks
     * @param foundTasks array list of all the tasks that contains the keyword
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + foundTasks.get(i));
            }
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}