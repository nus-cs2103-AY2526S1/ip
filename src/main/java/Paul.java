import java.util.Scanner;

public class Paul {
    public static final String LOGO = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;
    private static final TaskList list = new TaskList();

    private static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        list.mark(index);

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        list.unmark(index);

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm\n" + LOGO);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks in your list:");
                System.out.println(list);
                continue;
            }

            if (input.startsWith("mark ")) {
                markTask(input);
                continue;
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
                continue;
            }

            if (input.startsWith("todo ")) {
                list.add(new ToDo(input.substring(5)));
            } else if (input.startsWith("deadline ")) {
                String[] str = input.substring(9).split(" /by ", 2);
                list.add(new Deadline(str[0], str[1]));
            } else if (input.startsWith("event ")) {
                String[] str = input.substring(6).split(" /from | /to ");
                list.add(new Event(str[0], str[1], str[2]));
            } else {
                System.out.println("Invalid input!");
                continue;
            }

            System.out.println("Got it. I've added this task:");
            System.out.println(list.get(list.size()));
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
        sc.close();
    }
}
