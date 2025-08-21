import java.util.ArrayList;
import java.util.Scanner;

public class Note {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        String logo =
                " _   _       _        \n"
                        + "| \\ | | ___ | |_ ___  \n"
                        + "|  \\| |/ _ \\| __/ _ \\ \n"
                        + "| |\\  | (_) | || (_) |\n"
                        + "|_| \\_|\\___/ \\__\\___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Note");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();

            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks yet.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
            } else {
                // Add input to tasks
                tasks.add(input);
                System.out.println(" added: " + input);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
