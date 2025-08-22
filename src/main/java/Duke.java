//Imports
import java.util.Scanner;

public class Duke {
    private static final String LINESEP = "____________________________________________________________";
    private static final String EXIT = "bye";

    //Handles printing format
    private static void chatPrint(String txt) {
        //todo: handle multiline indents for all inputs
        System.out.println(String.format(
            "\t%s\n\t%s\n\t%s",
            Duke.LINESEP,
            txt,
            Duke.LINESEP
        ));
    }

    //Entrypoint
    public static void main(String[] args) {
        //Entry message
        chatPrint("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." + 
            "\n\t" + "What can I do for you?");

        //Work loop
        Scanner scanStdin = new Scanner(System.in);
        while (true) {
            String userCmd = scanStdin.nextLine();

            if (userCmd.equals(Duke.EXIT)) {
                chatPrint("Bye. Hope to see you again soon!");
                break;
            }
            chatPrint(userCmd);
        }
    }
}
