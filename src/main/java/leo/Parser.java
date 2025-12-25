package leo;

public class Parser {
    public Parser() {

    }

    /**
     * Interprets the different commands the user gave in the user interface
     * @param trimmed Input given by chatbot user, with the additional spaces removed
     * @return Command that indicates what action is to be taken with the interpreted input
     */
    public static Command parse(String trimmed) throws UnknownCommand {
        assert trimmed != null && !trimmed.isBlank() : "trimmed should not be null or an empty string";
        if (trimmed.equals("bye") || trimmed.equals("Bye")) {
            return new ExitCommand();

        } else if (trimmed.equals("list")) {
            return new ListCommand();
        } else if (trimmed.startsWith("mark")) {
            String[] parts = trimmed.split(" ");
            int value = Integer.parseInt(parts[parts.length - 1]);
            return new MarkCommand(value);
        } else if (trimmed.startsWith("unmark")) {
            String[] parts = trimmed.split(" ");
            int value = Integer.parseInt(parts[parts.length - 1]);
            return new UnmarkCommand(value);
        } else if (trimmed.startsWith("todo")) {
            String description = trimmed.replaceFirst("^\\s*todo\\b", "").trim();
            return new AddCommand(new ToDo(description));
        } else if (trimmed.startsWith("deadline")) {
            String[] parts = trimmed.split("/by", 2);
            String type = parts[0].trim();
            String description = type.replaceFirst("^\\s*deadline\\b", "").trim();
            String date = parts[1].trim();
            return new AddCommand(new Deadline(description, date));
        } else if (trimmed.startsWith("event")) {
            String[] firstSplit = trimmed.split("/from", 2);
            String description = firstSplit[0].replaceFirst("^\\s*event\\b", "").trim();
            String[] secondSplit = firstSplit[1].split("/to", 2);
            String start = secondSplit[0].trim();
            String to = secondSplit[1].trim();
            return new AddCommand(new Event(description, start, to));
        } else if (trimmed.startsWith("find")) {
            String item = trimmed.replaceFirst("^\\s*find\\b", "").trim();
            return new FindCommand(item);
        } else if (trimmed.startsWith("remind")) {
            String[] parts = trimmed.split(" ");
            int value = Integer.parseInt(parts[parts.length - 1]);
            return new RemindCommand(value);
        } else {
            throw new UnknownCommand("I cannot understand what you said");
        }
    }

    /**
     * Constructs the respective tasks by interpreting each line written into the file linked to the storage
     * @param line Each line of text in the file
     * @return Task, indicates the different types of tasks stored in the file
     */
    public static Task fromSaveFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        // check if it is a todo, deadline or event
        String type = parts[0];
        boolean done = "1".equals(parts[1]);

        switch (type) {
        case "T": {
            // T | 0 | read book
            ToDo t = new ToDo(parts[2]);
            if (done) {
                t.markAsDone();
            }
            return t;
        }
        case "D": {
            // D | 1 | return book | by=Sunday
            if (parts.length < 4) {
                return null;
            }
            String desc = parts[2];
            String by = parts[3].replaceFirst("^by=", "");
            Deadline d = new Deadline(desc, by);
            if (done) {
                d.markAsDone();
            }
            return d;
        }
        case "E": {
            // E | 0 | project meeting | from=Mon 2pm | to=4pm
            if (parts.length < 5) {
                return null;
            }
            String desc = parts[2];
            String from = parts[3].replaceFirst("^from=", "");
            String to = parts[4].replaceFirst("to=", "");
            Event e = new Event(desc, from, to);
            if (done) {
                e.markAsDone();
            }
            return e;
        }
        default:
            return null;
        }
    }
}
