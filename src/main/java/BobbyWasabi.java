import java.io.FileNotFoundException;
import java.net.CookieHandler;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class BobbyWasabi {

    public enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        OTHERS;

        public static Command toCommand(String input) {
            try {
                return Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Command.OTHERS;
            }
        }
    }

    private TaskList taskList;
    private Storage storage;

    /**
     * Checks if the integer given in the command from user is valid
     *
     * @param s String command from user
     * @param arrLen arrayLength of list of tasks
     * @return Boolean on whether the integer is true or not
     * @throws BobbyWasabiException
     */

    public static boolean isValidInteger(String s, int arrLen) throws BobbyWasabiException {
        String[] wordList = s.split(" ");

        // not valid command length
        if (wordList.length != 2) {
            throw new BobbyWasabiException("We only accept two inputs - the command and the integer");
        }


        // not a valid integer
        try {
            int indx = Integer.parseInt(wordList[1]);
            if (indx > arrLen) {
                throw new BobbyWasabiException("Index given in input is out of range, please try an index within the range of your list");
            }

        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Please input an index following your command");
        }

        return true;
    }

    /**
     * Returns the bot's string response when a task is added to the list
     *
     * @param task Task to be added
     * @param num Number of tasks in the list
     * @return The bot's respond when a task is added
     */
    public static String addTaskOutput(Task task, int num) {

        String s = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.
                ____________________________________________________________
                """,
                task, num);

        return s;
    }

    /**
     * Generates the bot's error message from the error message given
     *
     * @param e String error message
     * @return Bot's error message response
     */
    public static String generateErrorMsg(String e) {

        String s = String.format("""
                ____________________________________________________________
                OOPS!!! %s
                ____________________________________________________________
                """,
                e);

        return s;
    }


    /**
     * Parses the given string into a LocalDateTime class
     *
     * @param date String representation of date
     * @return LocalDateTime class
     * @throws DateTimeParseException
     */
    public static LocalDateTime parseDateString(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(date.trim(), formatter);
        return dateTime;
    }

    public static void main(String[] args) {
        // create scanner and the array of tasks
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/BobbyWasabiTasks.txt", "./data");
        TaskList list;
        try {
            storage.createDataStorage();
            list = new TaskList(storage.load());
        } catch (BobbyWasabiException e) {
            System.out.println(generateErrorMsg(e.getMessage()));
            list = new TaskList(new ArrayList<Task>());
        }


        // create the first bot greeting
        String decoLine = "____________________________________________________________";
        String botGreet = """
                ____________________________________________________________
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                ____________________________________________________________
                
                """;
        System.out.println(botGreet);

        while (true) {

            // Get user input
            String userInput = scanner.nextLine();

            Command command = Command.toCommand(userInput.split(" ")[0]);

            switch (command) {
            case BYE:
                System.out.println("""
                    ____________________________________________________________
                    Bye. Hope to see you again soon!
                    ____________________________________________________________
               
                    """);
                scanner.close();
                return;
            case LIST:

                String listOutput = decoLine + "\n" + "Here are the tasks in your list:\n" + list + decoLine;
                System.out.println(listOutput);
                continue;

            case MARK:
                try {
                    if (BobbyWasabi.isValidInteger(userInput, list.size())) {
                        String[] wordList = userInput.split(" ");
                        int indx = Integer.parseInt(wordList[1]);
                        Task targetTask = list.get(indx - 1);

                        targetTask.setIsMarked(true);
                        String curTask = String.format(
                                "%d. %s\n",
                                indx,
                                targetTask);

                        String markOutput = String.format("""
                                    Nice! I've marked this task as done:
                                       %s""",
                                curTask);

                        System.out.println(decoLine + "\n" + markOutput + decoLine);
                        storage.updateDataFileFromTasks(list);
                        continue;
                    }
                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            case UNMARK:
                try {
                    if (BobbyWasabi.isValidInteger(userInput, list.size())) {
                        String[] wordList = userInput.split(" ");
                        int indx = Integer.parseInt(wordList[1]);
                        Task targetTask = list.get(indx - 1);


                        targetTask.setIsMarked(false);
                        String curTask = String.format(
                                "%d. %s\n",
                                indx,
                                targetTask);

                        String output = String.format("""
                                    Nice! I've marked this task as not done yet:
                                       %s""",
                                curTask);

                        System.out.println(decoLine + "\n" + output + decoLine);
                        storage.updateDataFileFromTasks(list);
                        continue;
                    }
                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            case TODO:
                try {
                    String[] descriptions = userInput.split("todo ");

                    if (descriptions.length <= 1) {
                        throw new BobbyWasabiException("Plase provide a description for todo");
                    }

                    String description = descriptions[1];

                    if (description.trim().isEmpty()) {
                        throw new BobbyWasabiException("Plase provide a description for todo");
                    }

                    Task todo = new ToDo(description, false);

                    list.add(todo);

                    System.out.println(BobbyWasabi.addTaskOutput(todo, list.size()));
                    storage.fileWrite(todo.getData());
                    continue;
                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            case DEADLINE:
                try {
                    String[] bySplit = userInput.split("/by", 2);

                    if (bySplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the deadline!");
                    }

                    String[] descriptions = bySplit[0].split("deadline ");

                    if (descriptions.length < 2) {
                        throw new BobbyWasabiException("The deadline task description cannot be blank!");
                    }

                    if (descriptions[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("The deadline task description cannot be blank!");
                    }

                    String deadline = bySplit[1];

                    if (deadline.trim().isEmpty()) {
                        throw new BobbyWasabiException("The deadline cannot be blank!");
                    }

                    LocalDateTime dateTime = parseDateString(deadline);

                    Task deadlineTask = new Deadline(descriptions[1], false, dateTime);
                    list.add(deadlineTask);

                    System.out.println(BobbyWasabi.addTaskOutput(deadlineTask, list.size()));
                    storage.fileWrite(deadlineTask.getData());
                    continue;
                } catch (BobbyWasabiException | DateTimeParseException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }

            case EVENT:
                try {
                    String[] fromSplit = userInput.split("/from", 2);

                    // there was no /from command
                    if (fromSplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the start duration!");
                    }


                    String[] toSplit = fromSplit[1].split("/to", 2);

                    // there was no /to command
                    if (toSplit.length < 2) {
                        throw new BobbyWasabiException("You did not provide the end duration!");
                    }

                    // starting description is empty
                    if (toSplit[0].trim().isEmpty()) {
                        throw new BobbyWasabiException("Your starting duration is blank!");
                    } else if (toSplit[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("Your ending duration is blank!");
                    }


                    String[] descriptions = fromSplit[0].split("event ");

                    if (descriptions.length < 2) {
                        throw new BobbyWasabiException("The event description cannot be blank!");
                    }


                    if (descriptions[1].trim().isEmpty()) {
                        throw new BobbyWasabiException("You did not provide a description of your event!");
                    }

                    Task eventTask = new Event(descriptions[1], false, toSplit[0], toSplit[1]);
                    list.add(eventTask);

                    System.out.println(BobbyWasabi.addTaskOutput(eventTask, list.size()));
                    storage.fileWrite(eventTask.getData());
                    continue;

                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            case DELETE:
                try {
                    if (BobbyWasabi.isValidInteger(userInput, list.size())) {
                        String[] wordList = userInput.split(" ");
                        int indx = Integer.parseInt(wordList[1]);
                        Task targetTask = list.get(indx - 1);
                        list.remove(indx - 1);

                        String output = String.format("""
                            ____________________________________________________________
                            Noted. I've removed this task:
                                %s
                            Now you have %d tasks in the list
                            ____________________________________________________________
                            """,
                                targetTask, list.size());

                        System.out.println(output);
                        storage.updateDataFileFromTasks(list);
                        continue;
                    }

                } catch (BobbyWasabiException e) {
                    System.out.println(BobbyWasabi.generateErrorMsg(e.getMessage()));
                    continue;
                }
            case OTHERS:
                System.out.println(BobbyWasabi.generateErrorMsg("Please provide a valid command!"));
            }


        }

    }
}
