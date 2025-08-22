import java.util.Scanner;

public class Paul {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;

        System.out.println(line);
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
    }
}
