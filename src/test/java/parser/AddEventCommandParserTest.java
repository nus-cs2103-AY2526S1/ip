package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import moon.parser.exceptions.ParseException;
import moon.parser.usercommand.AddEventCommandParser;

public class AddEventCommandParserTest {
    @Test
    public void parse_emptyNameArgument_exceptionThrown() {
        try {
            AddEventCommandParser parser = new AddEventCommandParser();
            parser.parse("event ");
        } catch (ParseException e) {
            assertEquals("Meow! Your task name cannot be empty!"
                    + "\nYou should follow this syntax:\n"
                    + "event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}",
                    e.getMessage());
        }
    }

    @Test
    public void parse_emptyTimeArgument_exceptionThrown() {
        try {
            AddEventCommandParser parser = new AddEventCommandParser();
            parser.parse("event holiday /from 15/03/2025 /to");
        } catch (ParseException e) {
            assertEquals("Meow! Both your start and end time cannot be empty!"
                    + "\nYou should follow this syntax:\n"
                    + "event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}",
                    e.getMessage());
        }
    }

    @Test
    public void parse_missingArgument_exceptionThrown() {
        try {
            AddEventCommandParser parser = new AddEventCommandParser();
            parser.parse("event holiday /to 15/03/2025");
        } catch (ParseException e) {
            assertEquals("Meow! Are you missing a dash '/' or a command somewhere?"
                    + "\nYou should follow this syntax:\n"
                    + "event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}",
                    e.getMessage());
        }
    }

    @Test
    public void parse_wrongTimeKeyword_exceptionThrown() {
        try {
            AddEventCommandParser parser = new AddEventCommandParser();
            parser.parse("deadline read books /fro 15/03/2025 /to 26/4/2025");
        } catch (ParseException e) {
            assertEquals("Meow! I think you make a mistake here: 'fro'."
                    + "\nYou should follow this syntax:\n"
                    + "event {task description} /from dd/mm/yyyy HHMM {start time} /by dd/mm/yyyy HHMM {end time}",
                    e.getMessage());
        }
    }

    @Test
    public void parse_wrongTimeFormat_exceptionThrown() {
        try {
            AddEventCommandParser parser = new AddEventCommandParser();
            parser.parse("deadline read books /from 2025-03-15 /to 2025-04-26");
        } catch (ParseException e) {
            assertEquals("Could not parse date/time: 2025-03-15", e.getMessage());
        }
    }
}
