package parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Add;
import command.Command;
import command.Delete;
import command.Exit;
import command.Find;
import command.Greet;
import command.Invalid;
import command.ListOut;
import command.Mark;
import command.NotesAdd;
import command.NotesDelete;
import command.NotesList;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.ToDo;
import ui.Ui;

/**
 * Class in charge of converting string inputs into more usable classes
 */
public class Parser {
    /**
     * Helper function that converts date in string format to LocalDate object.
     * Accepts YYYY-MM-DD format. More format to be added in future
     * @param s Date in string format
     * @return Date in LocalDate format
     * @throws DukeException Handles errors during execution
     */
    public static LocalDate stringToDate(String s) throws DukeException {
        String re = "(\\d{4})-(\\d{2})-(\\d{2})";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(s.strip());

        try {
            if (m.find()) {
                int year = Integer.parseInt(m.group(1));
                int month = Integer.parseInt(m.group(2));
                int day = Integer.parseInt(m.group(3));
                return LocalDate.of(year, month, day);
            }
        } catch (DateTimeException e) {
            throw new DukeException("Invalid string used to convert to date! Attempted to convert " + s);
        }
        throw new DukeException("Invalid string format used to convert to date! Attempted to convert " + s);
    }

    /**
     * Handles converting user inputs into executable commands
     * @param s Input by users. Fixed to specified formats
     * @return  Corresponding command from input s. Invalid if unsuccessful.
     */
    public static Command parse(String s) {
        assert s != null;
        s = s.strip().toLowerCase();
        if (s.startsWith("note ")) {
            return Parser.handleNotes(s);
        } else if (s.equals("greet") || s.equals("hi")) {
            return new Greet();
        } else if (s.equals("bye") | s.equals("exit")) {
            return new Exit();
        } else if (s.equals("list")) {
            return new ListOut();
        } else if (s.startsWith("find") && s.length() > 5) {
            return new Find(s.substring(5));
        } else if (s.startsWith("delete")) {
            try {
                int index = Parser.extractIndex(s);
                return new Delete(index);
            } catch (DukeException e) {
                Ui.displayError(e);
            }
        } else if (s.startsWith("mark") | s.startsWith("unmark")) {
            try {
                int index = Parser.extractIndex(s);
                return new Mark(index, s.startsWith("mark"));
            } catch (DukeException e) {
                Ui.displayError(e);
            }
        } else if (s.startsWith("deadline") | s.startsWith("event") | s.startsWith("todo")) {
            try {
                return Parser.createAdd(s);
            } catch (DukeException e) {
                Ui.displayError(e);
            }
        }
        return new Invalid();
    }

    /**
     * Handle command for notes related tasks
     * @param s input string
     * @return Appropriate command
     */
    private static Command handleNotes(String s) {
        System.out.println(s);
        assert s.startsWith("note ");
        s = s.substring(5);
        if (s.startsWith("add ")) {
            String re = "add\\s+(.+)";
            Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            if (m.find()) {
                return new NotesAdd(m.group(1));
            }
        } else if (s.startsWith("delete ")) {
            try {
                int index = Parser.extractIndex(s);
                return new NotesDelete(index);
            } catch (DukeException e) {
                Ui.displayError(e);
            }
        } else if (s.startsWith("list")) {
            return new NotesList();
        }
        return new Invalid();
    }

    /**
     * Internal helper function that handles different types of events to be added.
     * @param s String input by user
     * @return  Add command if successful. Else Invalid.
     * @throws DukeException    Handles error in execution
     */
    private static Command createAdd(String s) throws DukeException {
        if (s.startsWith("todo") && s.length() > 5) {
            return new Add(new ToDo(s.substring(5)));
        } else if (s.startsWith("deadline")) {
            String re = "deadline\\s+(.+)\\s+/by\\s+(.+)";

            Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            if (m.find()) {
                try {
                    LocalDate date = Parser.stringToDate(m.group(2));
                    return new Add(new Deadline(m.group(1).strip(), date));
                } catch (DukeException e) {
                    throw new DukeException("While creating deadline - " + e.toString());
                }
            } else {
                throw new DukeException("Invalid deadline format used! Given " + s);
            }
        } else if (s.startsWith("event")) {
            String re = "event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)";

            Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            if (m.find()) {
                try {
                    LocalDate from = Parser.stringToDate(m.group(2));
                    LocalDate to = Parser.stringToDate(m.group(3));
                    return new Add(new Event(m.group(1).strip(), from, to));
                } catch (DukeException e) {
                    throw new DukeException("While creating event - " + e.toString());
                }
            } else {
                throw new DukeException("Invalid event format used! Given " + s);
            }
        } else {
            return new Invalid();
        }
    }

    /**
     * Internal helper function to extract index and check format for mark and delete
     * commands. Handles invalid formats.
     * @param s String command by user
     * @return  int index if successful
     * @throws DukeException    Handles error in execution. Specifically wrong formatting.
     */
    private static int extractIndex(String s) throws DukeException {
        String[] parts = s.split(" ");
        if (parts.length != 2) {
            throw new DukeException("Invalid format used for delete/mark command");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid integer used for delete/mark command");
        }
    }
}
