import java.util.Scanner;

public class Paul {
    public static final String LINE = "____________________________________________________________";
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

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
        System.out.println(LINE);
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        list.unmark(index);

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello I'm\n" + LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }

            if (input.equalsIgnoreCase("list")){
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                System.out.println(list);
                System.out.println(LINE);
                continue;
            }

            if (input.startsWith("mark ")) {
                markTask(input);
                continue;
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
                continue;
            }

            list.add(input);
            System.out.println(LINE);
            System.out.println("added: " + input);
            System.out.println(LINE);
        }
        sc.close();
    }
}
