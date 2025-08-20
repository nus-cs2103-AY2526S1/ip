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
                tasks.markTask(index);
            }
            else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                tasks.unmarkTask(index);
            }
            else {
                tasks.addTask(input);
            }
        }
        scanner.close();
    }
}
