package buddy;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Buddy");
        System.out.println(" What can I do for you?");
        showLine();
    }
    
    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }
    
    public String readCommand() {
        return scanner.nextLine();
    }
    
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    
    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }
    
    public void showTaskMarkedDone(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }
    
    public void showTaskMarkedNotDone(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }
    
    public void showTaskDeleted(Task task, int taskCount) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }
    
    public void showTaskList(Task[] tasks, int taskCount) {
        showLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        showLine();
    }
    
    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
        showLine();
    }
    
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with empty task list.");
    }
    
    public void showFoundTasks(Task[] foundTasks) {
        showLine();
        if (foundTasks.length == 0) {
            System.out.println(" No matching tasks found in your list.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.length; i++) {
                System.out.println(" " + (i + 1) + "." + foundTasks[i]);
            }
        }
        showLine();
    }
    
    public void close() {
        scanner.close();
    }
}