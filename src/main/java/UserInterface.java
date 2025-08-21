public class UserInterface {
    private final String line = "____________________________________________________________";
    private final Task task[];
    private Integer count = 0;

    public UserInterface() {
        task = new Task[100];
    }

    public void print(String input) {
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void sayHello() {
        print(" Hello! I'm Phuc\n" +
                " What can I do for you?");
    }

    public void sayGoodbye() {
        print(" Bye. Hope to see you again soon!");
    }

    public void list() {
        String temp = "Here are the tasks in your list:\n";
        for(int i=0; i<count; i++) {
            temp += Integer.toString(i+1) + ". ";
            temp += task[i].printTask() + "\n";
        }

        System.out.println(line);
        System.out.print(temp);
        System.out.println(line);
    }

    public void add(String newtask) {
        task[count] = new Task(newtask);
        print("Added: " + newtask);
        count++;
    }

    public void mark(String num) {
        int id = Integer.parseInt(num)-1;
        task[id].setDone();
        print("Nice! I've marked this task as done:\n" + task[id].printTask());
    }

    public void unMark(String num) {
        int id = Integer.parseInt(num)-1;
        task[id].setNotDone();
        print("OK, I've marked this task as not done yet:\n" + task[id].printTask());
    }

    public String notiNumOfTasks() {
        return "Now you have " + Integer.toString(count+1) + " tasks in the list.";
    }

    public String notiAddTasks() {
        return "Got it. I've added this task:\n";
    }

    public void toDo(String newtask) {
        task[count] = new ToDoTask(newtask);
        String temp = notiAddTasks() + task[count].printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void deadline(String newtask, String deadline) {
        task[count] = new DeadlineTask(newtask, deadline);
        String temp = notiAddTasks() + task[count].printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }

    public void event(String newtask, String from, String to) {
        task[count] = new EventTask(newtask, from, to);
        String temp = notiAddTasks() + task[count].printTask() + "\n" + this.notiNumOfTasks();
        print(temp);
        count++;
    }
}
