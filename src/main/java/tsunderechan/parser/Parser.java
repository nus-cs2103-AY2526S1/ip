package tsunderechan.parser;

import java.util.Scanner;

import tsunderechan.command.AddDeadlineCommand;
import tsunderechan.command.AddEventCommand;
import tsunderechan.command.AddTodoCommand;
import tsunderechan.command.Command;
import tsunderechan.command.DeleteCommand;
import tsunderechan.command.ElPsyCongrooCommand;
import tsunderechan.command.EmptyCommand;
import tsunderechan.command.ExitCommand;
import tsunderechan.command.FindCommand;
import tsunderechan.command.InvalidCommand;
import tsunderechan.command.ListCommand;
import tsunderechan.command.MadScientistCommand;
import tsunderechan.command.MarkCommand;
import tsunderechan.command.SteinsGateCommand;
import tsunderechan.command.UnmarkCommand;
import tsunderechan.command.WorldIsEndingCommand;
import tsunderechan.ui.Ui;

/**
 * Represents an object that can read and make sense of user input.
 */
public class Parser {
    /**
     * Returns a Command parsed from user input.
     * If the position is unset, NaN is returned.
     *
     * @param fullCommand The entire line typed by the user.
     * @param ui Ui used to read input and print output.
     * @return Command to be executed.
     * @throws IllegalArgumentException If input does not follow specified requirements.
     */
    public static Command parse(String fullCommand, Ui ui) {
        assert fullCommand != null : "fullCommand should not be null";

        String[] parsedCommand = setCommandAndArguments(fullCommand.trim().toLowerCase());

        String command = parsedCommand[0];
        String argument = parsedCommand[1];

        switch (command) {
        case "":
            return new EmptyCommand();
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return createMarkCommand(ui, argument);
        case "unmark":
            return createUnmarkCommand(ui, argument);
        case "delete":
            return createDeleteCommand(ui, argument);
        case "todo":
            return createTodoCommand(ui, command, argument);
        case "deadline":
            return createDeadlineCommand(ui, command, argument);
        case "event":
            return createEventCommand(ui, command, argument);
        case "find":
            return createFindCommand(ui, argument);
        case "el psy congroo":
            return new ElPsyCongrooCommand();
        case "the world is ending":
            return new WorldIsEndingCommand();
        case "steins gate":
            return new SteinsGateCommand();
        case "i am mad scientist":
            return new MadScientistCommand();
        default:
            return new InvalidCommand();
        }
    }


    /**
     * Returns the command and argument as a string array.
     * Used to find specific phrases instead of a single word command.
     * @param fullCommand The full command given by the user
     *
     * @return A string array where index 0 = command and index 1 = argument
     */
    private static String[] setCommandAndArguments(String fullCommand) {
        String command = "";
        String argument = "";
        if (fullCommand.startsWith("el psy congroo")) {
            command = "el psy congroo";
        } else if (fullCommand.startsWith("the world is ending")) {
            command = "the world is ending";
        } else if (fullCommand.startsWith("steins gate")) {
            command = "steins gate";
        } else if (fullCommand.startsWith("i am mad scientist")) {
            command = "i am mad scientist";
        } else {
            // fallback, take the first word
            Scanner sc = new Scanner(fullCommand);
            if (sc.hasNext()) {
                command = sc.next();
                argument = sc.hasNextLine() ? sc.nextLine().trim() : "";
            }
            sc.close();
        }
        return new String[]{command, argument};
    }

    private static Command createMarkCommand(Ui ui, String argument) {
        try {
            int index = Integer.parseInt(argument);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            ui.showMarkError();
            return new InvalidCommand(); // should not reach this line
        }
    }

    private static Command createUnmarkCommand(Ui ui, String argument) {
        try {
            int index = Integer.parseInt(argument);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            ui.showUnmarkError();
            return new InvalidCommand(); // should not reach this line
        }
    }

    private static Command createDeleteCommand(Ui ui, String argument) {
        try {
            int index = Integer.parseInt(argument);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            ui.showDeleteError();
            return new InvalidCommand(); // should not reach this line
        }
    }

    private static Command createTodoCommand(Ui ui, String command, String argument) {
        if (argument.isEmpty()) {
            ui.showInsufficientInformationError(command);
        }
        return new AddTodoCommand(argument);
    }

    private static Command createDeadlineCommand(Ui ui, String command, String argument) {
        if (argument.isEmpty()) {
            ui.showInsufficientInformationError(command);
        }
        String[] deadline = argument.split("/by", 2);
        if (deadline[0].trim().isEmpty()) {
            ui.showInsufficientInformationError(command);
        }
        if (deadline.length < 2) {
            ui.showDeadlineInvalidFormatError();
        }
        return new AddDeadlineCommand(deadline[0].trim(), deadline[1].trim());
    }

    private static Command createEventCommand(Ui ui, String command, String argument) {
        if (argument.isEmpty()) {
            ui.showInsufficientInformationError(command);
        }
        String[] event = argument.split("/from|/to", 3);
        if (event[0].isEmpty()) {
            ui.showInsufficientInformationError(command);
        }
        if (event.length < 3) {
            ui.showEventInvalidFormatError();
        }
        return new AddEventCommand(event[0].trim(), event[1].trim(), event[2].trim());
    }

    private static Command createFindCommand(Ui ui, String argument) {
        if (argument.isEmpty()) {
            ui.showNoKeywordDuringFind();
        }
        return new FindCommand(argument);
    }
}
