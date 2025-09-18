package chatowo;

import java.io.IOException;

import chatowo.task.Task;
import chatowo.task.TaskList;

/**
 * Main class that handles the chatbot functionality.
 * Manages tasks, user interactions, and file operations.
 */
public class Chatowo {
    // initialise task list
    private TaskList list = new TaskList();
    private final Storage storage = new Storage("./data/chatowo.txt");
    private final Parser parser = new Parser(this);
    private String prevCommand = null;
    private Task prevCommandTask = null;
    private int prevCommandIndex = -1;

    public String getResponse(String input) {
        return parser.parse(input);
    }

    public String listTasks() {
        return "Here are your tasks! >w<" + list;
    }

    public String dateTimeError() {
        return "Pwease put your date as yyyy-mm-dd format!! >m<";
    }

    /**
     * Adds the specified task into the task list.
     *
     * @param task Task to add
     */
    public String addTask(Task task) {
        try {
            list.add(task);
            storage.addTask(task);
            assert list.contains(task);

            this.prevCommand = "add";
            this.prevCommandTask = task;
            this.prevCommandIndex = list.size() - 1;

            return "Added " + task + " to task list! UwU\n"
                    + "You have " + list.size() + " tasks now!!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index Index of task to mark
     */
    public String mark(int index) {
        Task t = list.get(index);
        list.done(index);
        assert t.isDone();

        try {
            storage.editTaskList(index, t);
        } catch (Exception e) {
            return e.getMessage();
        }

        this.prevCommand = "mark";
        this.prevCommandTask = t;
        this.prevCommandIndex = index;

        return "Okie! This task is done! ^w^\n  " + t;
    }

    /**
     * Marks a task as undone at the specified index.
     *
     * @param index Index of task to mark
     */
    public String unmark(int index) {
        Task t = list.get(index);
        list.undone(index);
        assert !t.isDone();

        try {
            storage.editTaskList(index, t);
        } catch (Exception e) {
            return e.getMessage();
        }

        this.prevCommand = "unmark";
        this.prevCommandTask = t;
        this.prevCommandIndex = index;

        return "Oh... This task is not done yet... OwO\n  " + t;
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index Index of task to remove
     */
    public String delete(int index) {
        Task t = list.remove(index);
        list.trimToSize();

        try {
            storage.removeFromTaskList(index);

            this.prevCommand = "delete";
            this.prevCommandTask = t;
            this.prevCommandIndex = index;

            return "Okie! This task has been deleted! ^w^\n  " + t;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a string of tasks that matches the specified query.
     *
     * @param phrase Phrase to find in task
     */
    public String find(String phrase) {
        return "Here are the matching tasks! >w<" + list.find(phrase);
    }

    /**
     * Undoes the command that was last executed.
     */
    public String undo() {
        if (this.prevCommand == null) {
            return "I forgor what was the last command you did... Sorry >m<";
        }

        switch (this.prevCommand) {
        case "mark":
            return this.unmark(this.prevCommandIndex);
        case "unmark":
            return this.mark(this.prevCommandIndex);
        case "delete":
            return this.addTask(this.prevCommandTask);
        case "add":
            return this.delete(this.prevCommandIndex);
        default:
            assert false;
            return "";
        }
    }

    /**
     * Starts the chatbot and reads initial task list from storage.
     */
    public String init() {
        try {
            list = storage.readTaskList();
            return "Hewwo! I'm Chatowo. :3\nWhat can I do for you? OwO";
        } catch (IOException e) {
            return (e.getMessage());
        }
    }
}
