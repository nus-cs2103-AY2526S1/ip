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
    private static TaskList list = new TaskList();

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
                System.out.println(list);
                System.out.println(LINE);
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
