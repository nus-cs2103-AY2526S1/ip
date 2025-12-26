package gene;

import gene.command.*;
import gene.enums.Commands;
import gene.exceptions.CreateTaskException;
import gene.exceptions.EmptyTodoException;
import gene.exceptions.InvalidDeadlineException;
import gene.exceptions.InvalidEventException;
import gene.tasks.DeadlineTask;
import gene.tasks.EventTask;
import gene.tasks.TodoTask;

import java.time.format.DateTimeParseException;


public class Parser {
    /**
     * Returns a command that can then be executed. It will return a print
     * command with the error message if the input does not follow the
     * command style
     *
     * @param input Input from the user at the CLI
     * @return Intended command to be executed
     */
    public static Command parse(String input) {
        String[] inputArr = input.split(" ", 2);
        Command c = null;
        try {
            String commandStr = inputArr[0].toUpperCase();
            assert !commandStr.isEmpty();
            Commands command = Commands.valueOf(commandStr);
            switch (command) {
            case BYE:
                c = new ExitCommand();
                break;
            case LIST:
                c = new ListCommand();
                break;
            case MARK:
                c = new MarkCommand(Integer.parseInt(inputArr[1]));
                break;
            case UNMARK:
                c = new UnmarkCommand(Integer.parseInt(inputArr[1]));
                break;
            case DELETE:
                c = new DeleteCommand(Integer.parseInt(inputArr[1]));
                break;
            case FIND:
                c = new FindCommand(inputArr[1]);
                break;
            case TODO:
                if (inputArr.length < 2 || inputArr[1].isEmpty()) {
                    throw new EmptyTodoException();
                }
                c = new AddCommand(new TodoTask(inputArr[1], false));
                break;
            case DEADLINE:
                String[] parts = inputArr[1].split(" /by ", 2);
                if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new InvalidDeadlineException();
                }
                c = new AddCommand(new DeadlineTask(parts[0], parts[1], false));

                break;
            case EVENT:
                String[] fromSplit = inputArr[1].split(" /from ", 2);
                if (fromSplit.length < 2) {
                    throw new InvalidEventException();
                }
                String[] toSplit = fromSplit[1].split(" /to ", 2);
                if (toSplit.length < 2 || toSplit[0].isEmpty() || toSplit[1].isEmpty() || fromSplit[0].isEmpty()) {
                    throw new InvalidEventException();
                }
                c = new AddCommand(new EventTask(fromSplit[0], toSplit[0], toSplit[1], false));
                break;
            case REMIND:
                String[] part = inputArr[1].split("/by ", 2);
                if (part[1].isEmpty()) {
                    throw new CreateTaskException("AWWW MANNN Invalid remind format. Use: remind /by <date>");
                }
                System.out.println(part[1]);
                c = new RemindCommand(part[1]);
                break;
            default:
                //Empty as it will definitely be one of the above 3 types
                //If it is not, illegalArgumentException will be thrown at Commands.valueOf,
                //prior to switch statement
            }
        } catch (DateTimeParseException e) {
            c = new PrintCommand(Ui.SPACING + "AWWW MANNN Input date is in the wrong format! " +
                    "Please use the format YYYY-MM-DD HHMM");
        } catch (CreateTaskException e) {
            c = new PrintCommand(Ui.SPACING + e.getMessage());
        } catch (Exception e) {
            //Pokemon exception should be avoided
            //Used in this case since I always want to output something to user if error
            c = new PrintCommand(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }
}

