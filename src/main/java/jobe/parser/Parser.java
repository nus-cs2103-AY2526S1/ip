package jobe.parser;

import jobe.command.Command;
import jobe.command.CommandType;
import jobe.command.DeadlineCommand;
import jobe.command.DeleteCommand;
import jobe.command.EventCommand;
import jobe.command.FindCommand;
import jobe.command.ListCommand;
import jobe.command.MarkCommand;
import jobe.command.TodoCommand;
import jobe.command.UnmarkCommand;
import jobe.exception.JobeException;
import jobe.task.DeadlineTask;
import jobe.task.EventTask;
import jobe.task.Task;
import jobe.task.TaskType;
import jobe.task.TodoTask;

/**
 * Parser class to parse user's input into corresponding command classes.
 */
public class Parser {
    
    /**
     * Parses user's input into corresponding commands listed in CommandType enum.
     * Calls the corresponding command class with necessary inputs.
     *
     * @param input User's entire String input.
     * @return a Command object corresponding to user's input.
     * @throws JobeException If individual command classes throws an exception.
     */
    public static Command parse(String input) throws JobeException {
        assert input != null : "Input should never be null";
        assert !input.isBlank() : "Input should never be blank";
        String[] splitString = input.split(" ", 2);
        
        CommandType command = CommandType.stringToCommand(splitString[0]);
        assert command != null : "Command type should never be null";
        
        String args = splitString.length > 1 ? splitString[1].trim() : "";
        
        switch (command) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(args);
        case UNMARK:
            return new UnmarkCommand(args);
        case TODO:
            return new TodoCommand(args);
        case DEADLINE:
            return DeadlineCommand.createDeadlineCommand(args);
        case EVENT:
            return EventCommand.createEventCommand(args);
        case DELETE:
            return new DeleteCommand(args);
        case FIND:
            return new FindCommand(args);
        default:
            throw new JobeException("OOPS!!!! I'm Sorry, but I am not sure what you mean.");
        }
    }
    
    /**
     * Parses input when loading files into corresponding tasks.
     *
     * @param input Input from file containing user's tasks.
     * @return a Task object corresponding to input from file.
     * @throws JobeException If failed to parse tasks.
     */
    public static Task parseTask(String input) throws JobeException {
        String[] splitString = splitTask(input);
        
        TaskType taskType = TaskType.stringToCommand(splitString[0]);
        boolean isDone = splitString[1].equals("[X]");
        String taskDescription = splitString[2];
        
        switch (taskType) {
        case D:
            return new DeadlineTask(taskDescription, splitString[3], isDone);
        case E:
            return new EventTask(taskDescription, splitString[3], splitString[4], isDone);
        case T:
            return new TodoTask(taskDescription, isDone);
        default:
            throw new JobeException("OOPS!!!! Task parsing failed");
        }
    }
    
    /**
     * Parses input task index into and integer.
     *
     * @param args User input task index.
     * @return integer representing the task index.
     * @throws JobeException If user enters incorrect inputs.
     */
    public static int parseTaskIndex(String args) throws JobeException {
        try {
            if (args.isBlank()) {
                throw new JobeException("OOPS!!!! You have forgotten to input the task index!");
            }
            
            if (args.trim().contains(" ")) {
                throw new JobeException("OOPS!!!! You have input multiple indexes! Please only input one index.");
            }
            
            return Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new JobeException("OOPS!!!! The index must be a number!");
        }
    }
    
    /**
     * Splits input from file into its components.
     *
     * @param input input task from data file.
     * @return Array of strings containing individual components of a task.
     * @throws JobeException If an error occurs when reading the file input.
     */
    private static String[] splitTask(String input) throws JobeException {
        if (input == null || input.isBlank()) {
            throw new JobeException("OOPS!!!! Missing or invalid file input!");
        }
        
        String[] splitString = input.split(" / ");
        
        if (splitString.length < 3) {
            throw new JobeException("OOPS!!!! File input is incomplete or corrupted!");
        }
        
        return splitString;
    }
}

