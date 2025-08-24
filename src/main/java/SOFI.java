import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SOFI {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n" +
                "Hello! I'm SOFI\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<String> taskList = new ArrayList<>();

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println("____________________________________________________________");
            }
            else {
                taskList.add(userInput);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
    }
}

