//Imports
import java.util.Scanner;
import java.util.List;
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
    private static final String LINEINDENT = "    ";
    private static final String LINESEP = "____________________________________________________________";
    private enum ChatCommand {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
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
        //todo: does not check for empty txt string
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));

        System.out.println(Duke.LINEINDENT + Duke.LINESEP);
        lineStream.forEach(line -> System.out.println(Duke.LINEINDENT + line));
        System.out.println(Duke.LINEINDENT + Duke.LINESEP);
    }

    //LIST command
    private static void printChatHist(List<Task> chatHist) {
        //Can do some sanity check for whether there is any chat history items

        String hist = "Here are the tasks in your list:\n";
        int counter = 0;
        for (Task item : chatHist) {
            counter += 1;
            hist += String.format(
                "%d. %s\n",
                counter,
                item
            );
        }

        //Note: for static internal methods, should this function call be preceeded by 
        //the class name? e.g. Duke.chatPrint(...)
        chatPrint(hist.stripTrailing());
    }

    private static Todo createTodo(String taskDetail) {
        return new Todo(taskDetail);
    }

    private static Deadline createDeadline(String taskDetail) {
        String[] tmp = taskDetail.split(" /by ", 2);
        
        //exception thrown here indicates deadline was not provided by the user
        return new Deadline(tmp[0], tmp[1]);
    }

    private static Event createEvent(String taskDetail) {
        String[] tmp = taskDetail.split(" /from ", 2);
        //exception thrown here indicates start/end was not provided by the user
        String[] startEnd = tmp[1].split(" /to ", 2);
        
        return new Event(tmp[0], startEnd[0], startEnd[1]);
    }

    //Entrypoint
    public static void main(String[] args) {
        //Entry message
        chatPrint("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." + 
            "\n" + "What can I do for you?");

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

            Task task;
            switch (userCmd) {
                case ChatCommand.BYE:
                    exitFlag = true;
                    chatPrint("Bye. Hope to see you again soon!");
                    break;
                case ChatCommand.LIST:
                    printChatHist(chatHist);
                    break;
                
                //Fall over cases
                case ChatCommand.MARK:
                case ChatCommand.UNMARK:
                    /*
                    Can fail in 2 ways
                    1. user requested index is not integer
                    2. index does not exist
                    */
                    task = chatHist.get(
                        Integer.valueOf(userInput.split(" ", 2)[1]) - 1
                    );

                    if (userCmd == ChatCommand.MARK) {
                        chatPrint(String.format(
                            "Nice! I've marked this task as done:\n  %s",
                            task.setDone(true)
                        ));
                    } else {
                        chatPrint(String.format(
                            "OK, I've marked this task as not done yet:\n  %s",
                            task.setDone(false)
                        ));
                    }
                    break;
                
                case ChatCommand.TODO:
                case ChatCommand.DEADLINE:
                case ChatCommand.EVENT:
                    String taskDetail = userInput.split(" ", 2)[1];
                    if (userCmd == ChatCommand.TODO) {
                        task = createTodo(taskDetail);
                    } else if (userCmd == ChatCommand.DEADLINE) {
                        task = createDeadline(taskDetail);
                    } else {
                        task = createEvent(taskDetail);
                    }
                    chatHist.add(task);
                    chatPrint(String.format(
                        "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        task,
                        chatHist.size()
                    ));
                    break;
                
                default:
                    chatPrint("Unsupported Command: " + userInput);
            }
        }
    }
}
