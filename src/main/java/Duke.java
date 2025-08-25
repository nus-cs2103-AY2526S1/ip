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
        DELETE("delete"),
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
        //Sanity check
        if (taskDetail.isEmpty()) {
            throw new DukeException("Todo no description");
        }

        return new Todo(taskDetail);
    }

    private static Deadline createDeadline(String taskDetail) {
        String[] tmp = taskDetail.split(" /by ", 2);

        //Sanity check
        if (tmp.length != 2) {
            throw new DukeException("Deadline keyword /by missing");
        } else if (tmp[0].isEmpty()) {
            throw new DukeException("Deadline no description");
        } else if (tmp[1].isEmpty()) {
            throw new DukeException("/by missing value");
        }
        
        return new Deadline(tmp[0], tmp[1]);
    }

    private static Event createEvent(String taskDetail) {
        String[] tmp = taskDetail.split(" /from ", 2);

        //Sanity check
        if (tmp.length != 2) {
            throw new DukeException("Event keyword /from missing");
        } else if (tmp[0].isEmpty()) {
            throw new DukeException("Event no description");
        }

        String[] startEnd = tmp[1].split(" /to ", 2);

        //Sanity check
        if (startEnd.length != 2) {
            throw new DukeException("Event keyword /to missing");
        } else if (startEnd[0].isEmpty()) {
            throw new DukeException("/from missing value");
        } else if (startEnd[1].isEmpty()) {
            throw new DukeException("/to missing value");
        }
        
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
                case ChatCommand.DELETE:
                    /*
                    Can fail in 3 ways
                    1. user requested index is not integer
                    2. index does not exist
                    3. user did not provide an index
                    */
                    try {
                        task = chatHist.get(
                            Integer.valueOf(userInput.split(" ", 2)[1]) - 1
                        );

                        if (userCmd == ChatCommand.MARK) {
                            chatPrint(String.format(
                                "Nice! I've marked this task as done:\n  %s",
                                task.setDone(true)
                            ));
                        } else if (userCmd == ChatCommand.UNMARK) {
                            chatPrint(String.format(
                                "OK, I've marked this task as not done yet:\n  %s",
                                task.setDone(false)
                            ));
                        } else {
                            chatHist.remove(task);
                            chatPrint(String.format(
                                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                                task,
                                chatHist.size()
                            ));
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        chatPrint("No item requested");
                    } catch (NumberFormatException ex) {
                        chatPrint("Please specify by item number");
                    } catch (IndexOutOfBoundsException ex) {
                        chatPrint("Item does not exist in the list");
                    }
                    break;
                
                case ChatCommand.TODO:
                case ChatCommand.DEADLINE:
                case ChatCommand.EVENT:
                    try {
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
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        chatPrint("No details provided");
                    } catch (DukeException ex) {
                        chatPrint(ex.getMessage());
                    }
                    break;
                
                default:
                    chatPrint("Unsupported Command: " + userInput);
            }
        }
    }
}
