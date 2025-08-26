public class Parser {
    private final Ui ui;
    private final Storage storage;

    public Parser(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    private String[] parseSegments(String[] options) {
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

    public void handle(String input) throws ByteException {
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];

        switch (keyword) {
        case "bye":
            ui.showFarewell();
            break;
        case "list":
            ui.showTasks(storage.getTaskList().asList());
            break;
        case "todo": {
            String[] options = input.split(" ");
            String[] segment = parseSegments(options);
            if (segment[0] == null || segment[0].trim().equals("todo")) {
                throw new ByteException("Note that the description of a todo cannot be empty");
            }
            Task task = new Todo(segment[0]);
            storage.addTask(task);
            ui.showAddedTask(task, storage.getSize());
            break;
        }
        case "deadline": {
            String[] segment = parseSegments(input.split(" "));
            if (segment[0] == null || segment[0].trim().equals("deadline")) {
                throw new ByteException("The description of a deadline cant be empty");
            }
            if (segment[1] == null || segment[1].trim().isEmpty()) {
                throw new ByteException("The /by time of a deadline cant be empty");
            }
            Task task = new Deadline(segment[0], segment[1]);
            storage.addTask(task);
            ui.showAddedTask(task, storage.getSize());
            break;
        }
        case "event": {
            String[] segment = parseSegments(input.split(" "));
            if (segment[0] == null || segment[0].trim().equals("event")) {
                throw new ByteException("The description of an event cant be empty");
            }
            if (segment[1] == null || segment[1].trim().isEmpty()) {
                throw new ByteException("The /from time of an event cant be empty");
            }
            if (segment[2] == null || segment[2].trim().isEmpty()) {
                throw new ByteException("The /to time of an event cant be empty");
            }
            Task task = new Event(segment[0], segment[1], segment[2]);
            storage.addTask(task);
            ui.showAddedTask(task, storage.getSize());
            break;
        }
        case "mark": {
            int index = Integer.parseInt(parts[1]) - 1;
            storage.markTask(index);
            ui.showMarked(storage.getTask(index));
            break;
        }
        case "unmark": {
            int index = Integer.parseInt(parts[1]) - 1;
            storage.unmarkTask(index);
            ui.showUnmarked(storage.getTask(index));
            break;
        }
        case "delete": {
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ByteException("Specify the task number to delete");
            }
            int index = Integer.parseInt(parts[1].trim()) - 1;
            Task removed = storage.deleteTask(index);
            ui.showDeleted(removed, storage.getSize());
            break;
        }
        default:
            throw new ByteException("I dont know what that means!");
        }
    }
}


