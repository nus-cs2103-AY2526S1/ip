public class Parser {
    public static Command parse(String fullCommand) throws RainyException {
        String[] words = fullCommand.trim().split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (words.length < 2) {
                throw new RainyException("oh no!!! please specify which task number to mark.");
            }
            int markIndex = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            if (words.length < 2) {
                throw new RainyException("oh no!!! please specify which task number to unmark.");
            }
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new RainyException("oh no!!! the description of a todo cannot be empty.");
            }
            return new AddTodoCommand(words[1].trim());

        case "deadline":
            if (words.length < 2) {
                throw new RainyException("oh no!!! please specify task and deadline.");
            }
            String[] parts = words[1].split(" /by ");
            if (parts.length < 2) {
                throw new RainyException("oh no!!! please specify task and deadline.");
            }
            return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());

        case "event":
            if (words.length < 2) {
                throw new RainyException("oh no!!! please specify task from when to when.");
            }
            String[] eventParts = words[1].split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new RainyException("oh no!!! please specify task from when to when.");
            }
            return new AddEventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

        case "delete":
            if (words.length < 2) {
                throw new RainyException("oh no!!! please specify which task number to delete.");
            }
            int deleteIndex = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(deleteIndex);

        default:
            throw new RainyException("oh no!!! idk what that means... :-(");
        }
    }
}
