//Imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
Enum reference
https://stackoverflow.com/a/3978690
https://stackoverflow.com/a/604426
https://stackoverflow.com/a/59608518
https://stackoverflow.com/a/26118954
*/

public class Duke {
    private static final String LINESEP = "____________________________________________________________";
    private enum ChatCommand {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        UNKNOWNCMD("unrecognized command")
        ;

        private final String text;

        ChatCommand(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }

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
        ArrayList<Task> chatHist = new ArrayList<Task>();
        
        //Work loop
        while (!exitFlag) {
            String userInput = scanStdin.nextLine();
            
            //Look for any valid commands and set enum value
            ChatCommand userCmd;
            try {
                userCmd = ChatCommand.valueOf(
                    userInput.split(" ", 2)[0].toUpperCase()
                );
            } catch (IllegalArgumentException ex) {
                userCmd = ChatCommand.UNKNOWNCMD;
            }

            switch (userCmd) {
                case ChatCommand.BYE:
                    exitFlag = true;
                    chatPrint("Bye. Hope to see you again soon!");
                    break;
                case ChatCommand.LIST:
                    //todo: this print isnt perfect yet
                    Stream.<Integer>iterate(0, x -> x < chatHist.size(), x -> x + 1)
                        .forEach(x -> System.out.println(String.format(
                            "\t%d. %s",
                            x + 1,
                            chatHist.get(x)
                        )));
                    break;
                //Fall over cases
                case ChatCommand.MARK:
                case ChatCommand.UNMARK:
                    /*
                    Can fail in 2 ways
                    1. user requested index is not integer
                    2. index does not exist
                    */
                    Task task = chatHist.get(
                        Integer.valueOf(userInput.split(" ", 2)[1]) - 1
                    );

                    if (userCmd == ChatCommand.MARK) {
                        chatPrint(String.format(
                            "Nice! I've marked this task as done:\n\t  %s",
                            task.toggleDone()
                        ));
                    } else {
                        chatPrint(String.format(
                            "OK, I've marked this task as not done yet:\n\t  %s",
                            task.toggleDone()
                        ));
                    }
                    break;
                default:
                    chatHist.add(new Task(userInput));
                    chatPrint("added: " + userInput);
            }
        }
    }
}
