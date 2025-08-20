import java.util.Scanner;

public class Aurora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String intro = "Aurora: Hello! I'm Aurora.\nAurora: What can I do for you?";
        String outro = "Aurora: Bye. Hope to see you again soon!";

        System.out.println(intro);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("Aurora: " + input);
            input = scanner.nextLine();
        }

        System.out.println(outro);
    }
}
