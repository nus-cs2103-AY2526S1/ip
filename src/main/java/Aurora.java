import java.util.Scanner;

public class Aurora {

    public static final String INTRO = "Hello! I'm Aurora. What can I do for you?";
    public static final String OUTRO = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] list = new String[100];

        speak(INTRO);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            speak(input);
            input = scanner.nextLine();
        }

        speak(OUTRO);
        scanner.close();
    }

    public static void speak(String content) {
        System.out.println("Aurora: " + content);
    }
}
