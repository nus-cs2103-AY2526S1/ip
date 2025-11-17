package parser;

import customexceptions.*;

import listmanager.ListManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Parser is responsible for decoding Strings for user input as well as file input.
 *
 */
public class Parser {

    //Originally no enums were used
    //Consulted with AI on the effectiveness on using switch cases with enums over constants with if statements.
    private enum Command {
        BYE("bye"),
        LIST("list"),
        FIND("find"),
        UNMARK("unmark"),
        MARK("mark"),
        DELETE("delete"),
        TAG("tag"),
        UNTAG("untag"),
        ADD("");

        private final String commandString;

        Command(String commandString) {
            this.commandString = commandString;
        }

        public String getCommandString() {
            return  this.commandString;
        }
    }



    /**
     * Scans nextline to obtain userInput.
     * Takes the first word as the keyword to identify what command the user is issuing.
     * Calls executeCommand to execute the command
     *
     * @param listManager ListManager instance that stores tasks
     * @return False if the user wants to end the conversation, otherwise True.
     * @throws NoSuchTaskException If taskDescriptor in input does not match any known format.
     * @throws IncompleteTaskException If taskDescriptor in input matches known format but is incomplete.
     * @throws EmptyListException If user wants to display taskList but there are no tasks.
     * @throws NoSuchTagException If the tag the user wants to delete does not exist.
     */
    public String parseInput(ListManager listManager, String input)
            throws NoSuchTaskException, IncompleteTaskException, EmptyListException, NoSuchTagException {
        List<String> wordSegments = stringSplitter(input, " ");
        String keyword = wordSegments.get(0);

        Command command = stringToCommand(keyword);

        return executeCommand(listManager, input, command);

    }

    private String executeCommand(ListManager listManager, String input, Command command)
            throws NoSuchTaskException, IncompleteTaskException, EmptyListException, NoSuchTagException {
        switch (command) {
            case BYE:
                return command.getCommandString();
            case LIST:
                return listManager.displayList();
            case FIND:
                return listManager.findTasks(input);
            case MARK:
                return listManager.updateTask(true, input);
            case UNMARK:
                return listManager.updateTask(false, input);
            case DELETE:
                return listManager.deleteTasks(input);
            case TAG:
                return listManager.addTagToTask(input);
            case UNTAG:
                return listManager.removeTagFromTask(input);
            case ADD:
            default:
                return listManager.add(input);
        }
    }

    private Command stringToCommand(String keyword) {
        for (Command command: Command.values()) {
            if (keyword.equals(command.getCommandString())) {
                return command;
            }
        }
        return Command.ADD;
    }



    /**
     * This method breaks down the words into segments based on the specified splitPoints
     *
     * @param input is the String the caller wishes to break apart
     * @param splitPoints each split point will break the remaining string in half based on the first occurrence
     *                    of each splitPoint
     * @return a List of Strings that correspond to each segment of the input.
     */
    public List<String> stringSplitter(String input, String... splitPoints) {
        List<String> stringSegments = new ArrayList<String>();
        String temp = input;
        boolean completeSplit = true;

        for (String splitPoint : splitPoints) {
            String[] words = temp.split(splitPoint, 2);
            stringSegments.add(words[0]);
            if (words.length <= 1) {
                completeSplit = false;
                break;
            }
            temp = words[1];
        }

        if (completeSplit) {
            stringSegments.add(temp);
        }

        return stringSegments;
    }

}
