import java.util.Scanner;

public class Piper {
    public static void main(String[] args) {
        final String chatbot_Name = "Piper";
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        // greet user
        System.out.println(
                "____________________________________________________________\n"
                + "Hi! " + chatbot_Name + " here.\n"
                + "What shall we do today?\n"
                + "____________________________________________________________\n"
        );

        while (!exit) { // user is active
            String input = scanner.nextLine();
            if (input.equals("bye")) { // user is inactive
                System.out.println(
                        "Til next time!"
                );
                exit = true;
            } else {
                System.out.println(input); // echo user
            }
        }
    }
}