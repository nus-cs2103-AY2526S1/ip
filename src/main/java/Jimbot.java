import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Jimbot {
    public static void main(String[] args) {
        String greet = "   ____________________________________________________________\n" +
                        "    Hello! I'm Jimbot.\n" +
                        "    What can I do for you?\n" +
                        "   ____________________________________________________________";
        System.out.println(greet);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<String> userList = new ArrayList<>();


        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("   ____________________________________________________________\n" +
                        "    Bye. Hope to see you again soon!\n" +
                        "   ____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                int count = userList.size();
                String list = "";
                for (int i = 0; i < count; i++) {
                    list += "    " + (i + 1) + ". " + userList.get(i) + "\n";
                }
                System.out.println("   ____________________________________________________________\n" +
                        list +
                        "   ____________________________________________________________");
            } else {
                userList.add(userInput);
                System.out.println("   ____________________________________________________________\n" +
                "    added: " + userInput + "\n" +
                "   ____________________________________________________________");
            }
        }
        scanner.close();
    }
}
