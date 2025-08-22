import java.util.Scanner;
import java.util.Arrays;

public class John {
    private static Task[] list = new Task[100];
    private static int counter = 0;
    public static void main(String[] args) {
        command(new String[] {"start"});
        try (Scanner listen = new Scanner(System.in)) {
            while (true) {
                String line = listen.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] cmd = line.split("\\s+");
                command(cmd);
                if (cmd[0].equals("bye")) {
                    break;
                }
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
                if (counter == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + list[i]);
                    }
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
            case "todo":
                String todoDesc = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
                list[counter++] = new Todo(todoDesc);
                System.out.println("added: " + todoDesc);
                break;

            case "deadline":
                String dFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
                int byPos = dFull.lastIndexOf(" /by ");
                String dDesc = dFull.substring(0, byPos).trim();
                String dWhen = dFull.substring(byPos + 5).trim();
                list[counter++] = new Deadline(dDesc, dWhen);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[counter-1]);
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            case "event":
                String eFull = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));
                int fromPos = eFull.lastIndexOf(" /from ");
                int toPos = eFull.lastIndexOf(" /to ");
                String eDesc = eFull.substring(0, fromPos).trim();
                String eFrom = eFull.substring(fromPos + 7, toPos).trim();
                String eTo = eFull.substring(toPos + 5).trim();
                list[counter++] = new Event(eDesc, eFrom, eTo);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[counter-1]);
                System.out.println("Now you have " + counter + " tasks in the list.");
                break;

            default:
                list[counter] = new Task(String.join(" ", cmd));
                counter++;
                System.out.println("added: " + list[counter - 1]);
        }
        System.out.println("____________________________________________________________");
    }
}
