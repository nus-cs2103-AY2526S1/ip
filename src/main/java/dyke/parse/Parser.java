package dyke.parse;

import dyke.command.ByeCommand;
import dyke.command.DeadlineCommand;
import dyke.command.DeleteCommand;
import dyke.command.EventCommand;
import dyke.command.FindCommand;
import dyke.command.HelpCommand;
import dyke.command.ListCommand;
import dyke.command.MarkCommand;
import dyke.command.StatsCommand;
import dyke.command.TodoCommand;
import dyke.command.WhatCommand;
import dyke.data.Library;
import dyke.ui.Ui;

/**
 * Houses methods for parsing inputs from User
 */
public class Parser {
    private static final int ACCOUNT_INDEX = 1;
    private boolean isRunning = true;
    private Library library; // dyke.data.Library is Mutable
    private final Ui ui;
    private final Storage storage;

    /**
     * Parses commands from User
     * @param library {@code Library} containing {@code Task}s
     * @param ui {@code Ui} for CLI output
     * @param storage {@code Storage} to handle saved data
     */
    public Parser(Library library, Ui ui, Storage storage) {
        this.library = library;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Asserts that "Bye!" command has been invoked.
     * @return {@code boolean}
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Parses commands from User.
     * @param inputs The inputs from User
     */
    public String parseCommand(String[] inputs) {
        try {
            CommandType command = CommandType.fromString(inputs[0]);

            switch (command) {

            case BYE:
                ByeCommand bye = new ByeCommand();
                this.isRunning = false;
                return bye.execute(library, ui, storage);
            case LIST:
                return new ListCommand().execute(library, ui, storage);
            case HELP:
                return new HelpCommand().execute(library, ui, storage);
            case MARK:
                return new MarkCommand(Integer.parseInt(inputs[1]) - ACCOUNT_INDEX)
                    .execute(library, ui, storage);
            case DEADLINE:
                return new DeadlineCommand(inputs).execute(library, ui, storage);
            case TODO:
                return new TodoCommand(inputs).execute(library, ui, storage);
            case EVENT:
                return new EventCommand(inputs).execute(library, ui, storage);
            case DELETE:
                return new DeleteCommand(inputs)
                        .execute(library, ui, storage);
            case WHAT:
                return new WhatCommand().execute(library, ui, storage);
            case FIND:
                return new FindCommand(inputs).execute(library, ui, storage);
            case STATS:
                return new StatsCommand().execute(library, ui, storage);
            default:
                return "Dude, IDK whats that??"
                        + " type 'help' for help, bruv.\n"
                        + " Or you could just say, 'Bye!', like exactly.";
            }
        } catch (DykeException e) {
            String msg = e.getMessage() + "\n Dude, IDK whats that??"
                    + " type 'help' for help, bruv.\n"
                    + " Or you could just say, 'Bye!', like exactly.";
            ui.printMessage(msg);
            return msg;
        }
    }
}
