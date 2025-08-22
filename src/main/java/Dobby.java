import java.util.ArrayList;
import java.util.Scanner;

public class Dobby {
    private static ArrayList<String> userTexts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Dobby \n");
        System.out.println("What can I do for you? \n");
        boolean flag = true;

        while (flag) {
            System.out.print("> "); // prompt
            String input = sc.nextLine(); // read user input

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                flag = false;
            }
            if (input.equalsIgnoreCase("list")) {
                Dobby.listTexts();
            }
            else {
                Dobby.storeTexts(input);
            }
        }
    }

    public static void storeTexts(String userInput) {
        userTexts.add(userInput);
        System.out.print("added: " + userInput + "\n");
    }

    public static void listTexts() {
        int counter = 0;
        for (String t : userTexts) {
            counter++;
            System.out.println(counter + ". " + t);
        }
    }
}
