package friday;

import java.util.Scanner;

import friday.task.Task;

public class Ui {

    private Scanner scanner;
    private TaskList tasks;
    private boolean isRunning;
    private Parser parser;

    public Ui() {
        this.isRunning = true;
        this.tasks = new TaskList();
        this.scanner = new Scanner(System.in);
        this.parser = new Parser(this);
    }

    /**
     * Starts Friday's UI interactions
     */
    public void start() {

        System.out.println("-".repeat(50));
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println("You have " + this.getTaskCount() + " task(s) in the list.");

        while (this.isRunning) {
            System.out.println("-".repeat(50));
            String input = scanner.nextLine();

            System.out.println("-".repeat(50));
            parser.parse(input);
        }
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

    /**
     * Returns the number of tasks in the task list
     * @return
     */
    public Integer getTaskCount() {
        return this.tasks.count();
    }

    /**
     * Returns the task at the given index
     * @param index
     * @return
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param index
     * @return
     */
    public Task deleteTask(int index) {
        return this.tasks.delete(index);
    }

    public TaskList find(String keyword) {
        return this.tasks.find(keyword);
    }

    /**
     * Finds tasks by their completion status
     * @param isDone true for completed tasks, false for pending tasks
     * @return TaskList containing tasks with the specified status
     */
    public TaskList findByStatus(boolean isDone) {
        return this.tasks.findByStatus(isDone);
    }

    /**
     * Finds tasks by their type
     * @param taskType The type of task to find
     * @return TaskList containing tasks of the specified type
     */
    public TaskList findByType(String taskType) {
        return this.tasks.findByType(taskType);
    }

    /**
     * Stops Friday's UI interactions
     */
    public void stop() {
        this.scanner.close();
        this.isRunning = false;
        this.tasks.save();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
