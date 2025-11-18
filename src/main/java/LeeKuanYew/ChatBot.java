package LeeKuanYew;

import java.util.Arrays;

import LeeKuanYew.Command.Command;
import LeeKuanYew.Command.ByeCommand;
import LeeKuanYew.Command.ListCommand;
import LeeKuanYew.Command.MarkCommand;
import LeeKuanYew.Command.DeleteCommand;
import LeeKuanYew.Command.SaveCommand;
import LeeKuanYew.Command.FindCommand;
import LeeKuanYew.Command.UpdateCommand;
import LeeKuanYew.Command.ToDoCommand;
import LeeKuanYew.Command.DeadlineCommand;
import LeeKuanYew.Command.EventCommand;

public class ChatBot {

    /**
     * Parses the given user input string and returns the corresponding Command.
     *
     * Recognised commands include bye, list, mark, unmark, delete, save, todo, deadline, and event.
     *
     * @param input the full user input string to parse
     * @return a Command representing the parsed input
     * @throws Exception if the input is invalid or does not match any command
     */
    public Command parseCommand(String input) throws Exception {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return handleMark(parts, true);

        case "unmark":
            return handleMark(parts, false);

        case "delete":
            if (parts.length < 2) {
                throw new Exception("You must specify a task number!");
            }

            try {
                int k = Integer.parseInt(parts[1]) - 1;
                return new DeleteCommand(k);
            } catch (NumberFormatException e) {
                throw new Exception("This is not a number.");
            }

        case "updatedeadline":
            if (parts.length < 2) {
                throw new Exception("You must specify a task number!");
            }

            try {
                String[] updateParts = parts[1].split(" /", 2);
                if (updateParts.length != 2) {
                    throw new Exception("Discipline requires deadlines.");
                }
                int k = Integer.parseInt(updateParts[0].trim()) - 1;
                return new UpdateCommand(k, updateParts[1]);
            } catch (NumberFormatException e) {
                throw new Exception("This is not a number.");
            }

        case "save":
            return new SaveCommand();
            
        case "find":
            return new FindCommand(parts[1]);

        case "todo":
            return new ToDoCommand(parts[1]);

        case "deadline":
            String[] deadlineParts = parts[1].split("/", 2);
            if (deadlineParts.length != 2) throw new Exception("Discipline requires deadlines.");

            return new DeadlineCommand(deadlineParts[0], deadlineParts[1]);

        case "event":
            String[] eventParts = parts[1].split("/", 3);
            if (eventParts.length != 3) throw new Exception("It takes time to build a country like Singapore.");

            return new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
            
        default:
            throw new Exception("YOUR WORDS MEAN NOTHING!");
        }

    }

    private MarkCommand handleMark(String[] input, boolean isMark) throws Exception {
        if (input.length < 2) {
            throw new Exception("You must specify a task number!");
        }

        try {
            int j = Integer.parseInt(input[1]) - 1;
            return new MarkCommand(j, isMark);
        } catch (NumberFormatException e) {
            throw new Exception("This is not a number.");
        }
    }
}
