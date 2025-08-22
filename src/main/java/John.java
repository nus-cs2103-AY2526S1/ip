import java.util.Scanner;

public class John {
    private static String[] list = new String[100];
    private static int counter = 0;
    public static void main(String[] args) {
        command("start");
        Scanner listen = new Scanner(System.in);
        while (true) {
            String cmd = listen.nextLine();
            command(cmd);
            if (cmd.equals("bye")) {
                break;
            }
        }
    }

    public static void command(String cmd) {
        switch (cmd) {
            case "start":
                System.out.println("Hello! I'm John");
                System.out.println("What can I do for you?");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                for (int i = 0; i < counter; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + list[i]);
                }
                break;
            default:
                list[counter] = cmd;
                counter++;
                System.out.println("added: " + cmd);
        }
    }
}
