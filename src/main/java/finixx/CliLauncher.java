package finixx;

import java.util.Scanner;

/**
 * The CLI launcher for the Finixx application.
 * It initializes the Finixx instance and handles user input/output in the console.
 * This class is intended for text-ui-test purposes.
 */
public class CliLauncher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Finixx finixx = new Finixx("/data/test_tasks.txt"); // use a test file
        boolean isRunning = true;

        while (isRunning && sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            String response = finixx.getResponse(input);
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(response);
            System.out.println("-------------------------------------------------------------------------------------");

            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
            }
        }
        sc.close();
    }
}
