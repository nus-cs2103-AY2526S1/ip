public class Userinterface {
    private final String line = "____________________________________________________________";
    private final Task task[];
    private Integer count = 0;

    public Userinterface() {
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

    public void echo(String input) {
        print(input);
    }

    public void list() {
        String temp = "";
        for(int i=0; i<count; i++) {
            temp += Integer.toString(i+1) + ". ";
            temp += task[i].printtask() + "\n";
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
        print("Nice! I've marked this task as done:\n" + task[id].printtask());
    }

    public void unmark(String num) {
        int id = Integer.parseInt(num)-1;
        task[id].setNotDone();
        print("OK, I've marked this task as not done yet:\n" + task[id].printtask());
    }
}
