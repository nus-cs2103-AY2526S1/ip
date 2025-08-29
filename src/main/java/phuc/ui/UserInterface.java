package phuc.ui;

import phuc.model.TaskList;
import phuc.storage.Storage;
import phuc.exception.PhucException;
import phuc.model.DeadlineTask;
import phuc.model.EventTask;
import phuc.model.ToDoTask;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserInterface {
    private static final String line = "____________________________________________________________";
    private final TaskList task;
    private Integer count = 0;
    private final Storage storage;

    public UserInterface() {
        this.task = new TaskList();
        this.storage = new Storage();
    }

    public void load() throws PhucException {
        try {
            task.clear();
            task.addAll(storage.load().getAllTasks());
            count = task.size();
        } catch (IOException e) {
            throw new PhucException("Failed to load tasks: " + e.getMessage());
        }
    }

    public void saveToStorage() throws PhucException {
        try {
            storage.save(task);
        } catch (IOException e) {
            throw new PhucException("Failed to save tasks: " + e.getMessage());
        }
    }

    public int numTasks() {
        return count+1;
    }

    public void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void sayHello() {
        print(" Hello! I'm Phuc \uD83D\uDE03\uD83D\uDD90\uFE0F\n" +
                " What can I do for you?");
    }

    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon! \uD83E\uDD27");
    }

    public void list() {
        String temp = "Here are the tasks in your list:\n";
        for(int i=0; i<count; i++) {
            temp += (i+1) + ". ";
            temp += task.get(i) + "\n";
        }

        System.out.println(line);
        System.out.print(temp);
        System.out.println(line);
    }

    public void mark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setDone();
        print("Nice! I've marked this task as done:\n" + task.get(id));
    }

    public void unMark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setNotDone();
        print("OK, I've marked this task as not done yet:\n" + task.get(id));
    }

    public String notiNumOfTasks() {
        return "Now you have " + (count+1) + " tasks in the list.";
    }

    public String notiAddTasks() {
        return "Got it. I've added this task:\n";
    }

    public void toDo(String newtask) {
        task.add(new ToDoTask(newtask));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void deadline(String newtask, LocalDateTime deadline) {
        task.add(new DeadlineTask(newtask, deadline));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void event(String newtask, LocalDateTime startDate, LocalDateTime endDate) {
        task.add(new EventTask(newtask, startDate, endDate));
        String temp = notiAddTasks() + task.get(count) + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void delete(String num) {
        count-=2;
        String temp = "Noted. I've removed this task:\n" +
                    task.get(Integer.parseInt(num)-1) + "\n" +
                    this.notiNumOfTasks();
        task.remove(Integer.parseInt(num)-1);
        print(temp);
        count++;
    }

    public static void showError(String error) {
        System.out.println(line);
        System.out.println(error);
        System.out.println(line);
    }
}
