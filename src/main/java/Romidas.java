import java.util.Scanner;

public class Romidas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Romidas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
