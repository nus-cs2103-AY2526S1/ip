import java.util.Scanner;
import java.util.ArrayList;

public class Romidas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> store = new ArrayList<>();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Romidas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println(i + 1 + ". " + store.get(i));
                }
            }
            else{
                store.add(input);
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
