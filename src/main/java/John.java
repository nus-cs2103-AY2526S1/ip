import java.util.Scanner;

public class John {
    private static Task[] list = new Task[100];
    private static int counter = 0;
    public static void main(String[] args) {
        command(new String[] {"start"});
        Scanner listen = new Scanner(System.in);
        while (true) {
            String[] cmd = listen.nextLine().split("\\W+");
            command(cmd);
            if (cmd[0].equals("bye")) {
                break;
            }
        }
    }

    public static void command(String[] cmd) {
        switch (cmd[0]) {
            case "start":
                System.out.println("Hello! I'm John");
                System.out.println("What can I do for you?");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + list[i]);
                }
                break;
            case "mark":
                list[Integer.parseInt(cmd[1]) - 1].markAsComplete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[Integer.parseInt(cmd[1]) - 1]);
                break;
            case "unmark":
                list[Integer.parseInt(cmd[1]) - 1].markAsIncomplete();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[Integer.parseInt(cmd[1]) - 1]);
                break;
            default:
                list[counter] = new Task(String.join(" ", cmd));
                counter++;
                System.out.println("added: " + list[counter - 1]);
        }
        System.out.println("____________________________________________________________");
    }
}
