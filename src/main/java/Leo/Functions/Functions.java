package Leo.Functions;

import Leo.Display.Messages;
import Leo.Functions.Commands.AddTaskCommand;
import Leo.Functions.Commands.DeleteTaskCommand;
import Leo.Functions.Commands.ListTaskCommand;
import Leo.Functions.Commands.MarkTaskCommand;
import Leo.Functions.Search.Find;
import Leo.Functions.Task.TaskList;
import Leo.ZeroLengthException;


import java.io.IOException;
import java.time.DateTimeException;

/**
 * The Functions class calls the list operation based on the user input given.
 */
public class Functions {
    TaskList listItems;

    /**
     * Instantiates the Functions object.
     *
     * @param listItems List of tasks.
     */
    public Functions(TaskList listItems) {
        this.listItems = listItems;
    }

    /**
     * The method calls the function based on the user input.
     *
     * @param userInput User input.
     * @throws ZeroLengthException If the list is empty.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     * @throws DateTimeException If the date or time is given in the wrong format.
     * @throws NumberFormatException If the input format is incorrect
     */
    //Solution adapted from https://www.perplexity.ai/search/catch-a-function-but-handle-it-prjjRGnZRsu8igx_P1RE7A
    public void SearchFunctions(String userInput) throws NumberFormatException, ZeroLengthException, IndexOutOfBoundsException, IOException, DateTimeException{
        String[] userInputList = userInput.split(" ");
        switch(userInputList[0].trim()) {
            case "list":
                ListTaskCommand.list(listItems);
                break;
            case "bye":
                Messages.Goodbye();
                break;
            case "mark":
                try {
                    MarkTaskCommand.markTask(Integer.parseInt(userInputList[1].trim()), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to mark.");
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(
                            "Incorrect format provided."
                            + "\n\tMake sure it is in this format:"
                            + "\n\t\tunmark <task index>"
                    );
                }
                break;
            case "unmark":
                try {
                    MarkTaskCommand.unmarkTask(Integer.parseInt(userInputList[1].trim()), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to unmark.");
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(
                            "Incorrect format provided."
                            + "\n\tMake sure it is in this format:"
                            + "\n\t\tunmark <task index>"
                    );
                }
                break;
            case "todo":
                AddTaskCommand.todo(userInput, listItems);
                break;
            case "deadline":
                AddTaskCommand.deadline(userInput, listItems);
                break;
            case "event":
                AddTaskCommand.event(userInput, listItems);
                break;
            case "delete":
                try {
                    DeleteTaskCommand.deleteTask(Integer.parseInt(userInputList[1]), listItems);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Missing list number of task to delete.");
                }
                break;
            case "find":
                Find.find(userInput, listItems);
                break;
            default:
                System.out.println("\tI don't know how to do this...");
                break;
        }
    }
}
