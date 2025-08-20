import java.util.Scanner;

public class EvansBot {

    public static void main(String[] args) {
        Greet greeter = new Greet("EvansBot");
        Exit exiter = new Exit();
        TaskList tasks = new TaskList(100); //size 100 array
        Scanner scanner = new Scanner(System.in);
        String input;

        greeter.greet();
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exiter.sayBye();
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                tasks.listTasks();
            }
            else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index > tasks.getCount()) {
                    System.out.println("There are less than " + index + " tasks in the list.");
                } else {
                    tasks.markTask(index);
                }
            }
            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index > tasks.getCount()) {
                    System.out.println("There are less than " + index + " tasks in the list.");
                } else {
                    tasks.unmarkTask(index);
                }
            }
            else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks.addTask(new ToDo(description));
            }
            else if (input.startsWith("deadline ")) {
                //split by /by
                String[] information = input.substring(9).split(" /by", 2);
                String description = information[0];
                String by = information[1];
                tasks.addTask(new Deadline(description, by));
            }
            else if (input.startsWith("event ")) {
                //split by /from and /to
                String[] information = input.substring(6).split(" /from | /to ", 3);
                String description = information[0];
                String from = information[1];
                String to = information[2];
                tasks.addTask(new Event(description, from, to));
            }
            else {
                System.out.println(input);
            }
        }
        scanner.close();
    }
}
