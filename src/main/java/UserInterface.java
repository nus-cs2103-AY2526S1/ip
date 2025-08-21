import java.util.ArrayList;

public class UserInterface {
    private static final String line = "____________________________________________________________";
    private final ArrayList<Task> task;
    private Integer count = 0;

    public UserInterface() {
        task = new ArrayList<>(100);
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
            temp += Integer.toString(i+1) + ". ";
            temp += task.get(i).printTask() + "\n";
        }

        System.out.println(line);
        System.out.print(temp);
        System.out.println(line);
    }

    public void mark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setDone();
        print("Nice! I've marked this task as done:\n" + task.get(id).printTask());
    }

    public void unMark(String num) {
        int id = Integer.parseInt(num)-1;
        task.get(id).setNotDone();
        print("OK, I've marked this task as not done yet:\n" + task.get(id).printTask());
    }

    public String notiNumOfTasks() {
        return "Now you have " + Integer.toString(count+1) + " tasks in the list.";
    }

    public String notiAddTasks() {
        return "Got it. I've added this task:\n";
    }

    public void toDo(String newtask) {
        task.add(new ToDoTask(newtask));
        String temp = notiAddTasks() + task.get(count).printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void deadline(String newtask, String deadline) {
        task.add(new DeadlineTask(newtask, deadline));
        String temp = notiAddTasks() + task.get(count).printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void event(String newtask, String from, String to) {
        task.add(new EventTask(newtask, from, to));
        String temp = notiAddTasks() + task.get(count).printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void delete(String num) {
        count-=2;
        String temp = "Noted. I've removed this task:\n" +
                    task.get(Integer.parseInt(num)-1).printTask() + "\n" +
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
