import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String greet = "   ____________________________________________________________\n" +
                        "   Hello! I'm Jimbot.\n" +
                        "   What can I do for you?\n" +
                        "   ____________________________________________________________";
        System.out.println(greet);
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("   ____________________________________________________________\n" +
                        "   Bye. Hope to see you again soon!\n" +
                        "   ____________________________________________________________");
                break;
            } else {
                System.out.println("   ____________________________________________________________\n" +
                "   " + userInput + "\n" +
                "   ____________________________________________________________");
            }
        }
        scanner.close();
    }
}
