public class Parser {

    private static String[] parseSegments(String[] options) {
        String[] segments = new String[3];
        StringBuilder currentSegment = new StringBuilder();
        int segmentIndex = 0;

        for (String option : options) {
            if (option.startsWith("/")) {
                segments[segmentIndex] = currentSegment.toString().trim();
                segmentIndex++;
                currentSegment.setLength(0);
            } else {
                if (!currentSegment.isEmpty()) {
                    currentSegment.append(" ");
                }
                currentSegment.append(option);
            }
        }

        if (segmentIndex < segments.length) {
            segments[segmentIndex] = currentSegment.toString().trim();
        }

        return segments;
    }

    public static Command parse(String input) throws ByteException {
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];

        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo": {
            String[] options = input.split(" ");
            String[] segment = parseSegments(options);
            String description = (segment[0] != null && !segment[0].trim().equals("todo")) ? segment[0] : "";
            return new TodoCommand(description);
        }
        case "deadline": {
            String[] segment = parseSegments(input.split(" "));
            String description = (segment[0] != null && !segment[0].trim().equals("deadline")) ? segment[0] : "";
            String by = segment[1];
            return new DeadlineCommand(description, by);
        }
        case "event": {
            String[] segment = parseSegments(input.split(" "));
            String description = (segment[0] != null && !segment[0].trim().equals("event")) ? segment[0] : "";
            String from = segment[1];
            String to = segment[2];
            return new EventCommand(description, from, to);
        }
        case "mark": {
            int index = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(index);
        }
        case "unmark": {
            int index = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(index);
        }
        case "delete": {
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ByteException("Specify the task number to delete");
            }
            int index = Integer.parseInt(parts[1].trim()) - 1;
            return new DeleteCommand(index);
        }
        default:
            throw new ByteException("I dont know what that means!");
        }
    }
}


