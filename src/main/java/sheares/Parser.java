package sheares;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import sheares.command.AddCommand;
import sheares.command.Command;
import sheares.command.DeleteCommand;
import sheares.command.ExitCommand;
import sheares.command.FindCommand;
import sheares.command.ListCommand;
import sheares.command.MarkCommand;
import sheares.command.UnMarkCommand;
import sheares.exception.DukeException;
import sheares.exception.NumberOfArgumentsException;
import sheares.exception.WrongInputException;

/**
 * Helper class that stores parsing logic from string commands to executables
 */
public class Parser {
    /**
     * Outputs the corresponding command to be executed given a String input
     * @param input
     * @return
     * @throws DukeException
     * @throws DateTimeParseException
     */
    public static Command parse(String input) throws DukeException, DateTimeParseException {
        assert !input.isEmpty();
        String[] line = input.split(" ");
        String command = line[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int index = Integer.parseInt(line[1]);
            //Since mark 8 for example corresponds to the 8th task, which is index 7
            //of our task list, we have to take index - 1 (same for unmark and delete)
            int markIndex = index - 1;
            return new MarkCommand(markIndex);
        case "unmark":
            int index2 = Integer.parseInt(line[1]);
            int unmarkIndex = index2 - 1;
            return new UnMarkCommand(unmarkIndex);
        case "delete":
            int index3 = Integer.parseInt(line[1]);
            int deleteIndex = index3 - 1;
            return new DeleteCommand(deleteIndex);
        case "find":
            if (!input.contains("find ")) {
                throw new NumberOfArgumentsException("find");
            }
            String[] arr7 = input.split("find ");
            String keyWord = arr7[1];
            return new FindCommand(keyWord);
        case "todo":
            if (!input.contains("todo ")) {
                throw new NumberOfArgumentsException("todo");
            }
            String[] arr = input.split("todo ");
            String taskToDo = arr[1];
            return new AddCommand(taskToDo);
        case "deadline":
            if (!input.contains("deadline ")) {
                throw new NumberOfArgumentsException("deadline");
            }
            String[] arr2 = input.split("deadline ");
            String info2 = arr2[1];
            if (!info2.contains(" /by ")) {
                throw new NumberOfArgumentsException("deadline");
            }
            String[] arr3 = info2.split(" /by ");
            String description = arr3[0];
            LocalDate deadline = LocalDate.parse(arr3[1]);
            return new AddCommand(description, deadline);
        case "event":
            if (!input.contains("event ")) {
                throw new NumberOfArgumentsException("event");
            }
            String[] arr4 = input.split("event ");
            String info3 = arr4[1];
            if (!info3.contains(" /from ")) {
                throw new NumberOfArgumentsException("event");
            }
            String[] arr5 = info3.split(" /from ");
            String description2 = arr5[0];
            String rest = arr5[1];
            if (!rest.contains(" /to ")) {
                throw new NumberOfArgumentsException("event");
            }
            String[] last = rest.split(" /to ");
            String from = last[0];
            String to = last[1];
            return new AddCommand(description2, from, to);
        case "fixed":
            if (!input.contains("fixed ")) {
                throw new NumberOfArgumentsException("fixed");
            }
            String[] arr6 = input.split("fixed ");
            String info4 = arr6[1];
            if (!info4.contains(" /duration ")) {
                throw new NumberOfArgumentsException("fixed");
            }
            String[] arr8 = info4.split(" /duration ");
            String description3 = arr8[0];
            String duration = arr8[1];
            return new AddCommand(description3, duration);
        default:
            throw new WrongInputException();
        }
    }
}
