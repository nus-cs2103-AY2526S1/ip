import java.util.Scanner;

public class Byte {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String greetMessage = "Hello! I'm Byte.\nWhat can I do for you?\n";

        System.out.println(line + greetMessage + line);

        Scanner scanner = new Scanner(System.in);

        String command = "";
        while (!command.equals("bye")) {
        	command = scanner.nextLine();
        	System.out.println(reply(command, line));
        }
        scanner.close();
    }


    public static String reply(String command, String line) {
        switch (command) {
        case "bye":
            return "\t" + line + "\t" + "Bye, hope to see you again soon!\n" + "\t" + line;
        default:
            return "\t" + line + "\t" + command + "\n" + "\t" + line;
        }
    }
}
