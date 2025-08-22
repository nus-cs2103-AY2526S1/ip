//Imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Duke {
    private static final String LINESEP = "____________________________________________________________";
    private static final String EXIT = "bye";
    private static final String LIST = "list";

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

        //Work loop vars
        Scanner scanStdin = new Scanner(System.in);
        boolean exitFlag = false;
        ArrayList<String> chatHist = new ArrayList<String>();
        
        //Work loop
        while (!exitFlag) {
            String userCmd = scanStdin.nextLine();

            switch (userCmd) {
                case Duke.LIST:
                    Stream.<Integer>iterate(0, x -> x < chatHist.size(), x -> x + 1)
                        .forEach(x -> System.out.println(String.format(
                            "\t%d. %s",
                            x + 1,
                            chatHist.get(x)
                        )));
                    break;
                case Duke.EXIT:
                    exitFlag = true;
                    chatPrint("Bye. Hope to see you again soon!");
                    break;
                default:
                    chatHist.add(userCmd);
                    chatPrint("added: " + userCmd);
            }
        }
    }
}
