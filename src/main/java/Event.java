public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Task toTask(String[] parts) throws RomidasException {
        if (parts.length != 4) {
            throw new RomidasException("Invalid number of arguments. Expected 4 but got " + parts.length);
        }
        String[] timeParts = parts[3].split("-");
        if (timeParts.length != 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
            throw new RomidasException("Invalid event format. Expected 'from-to' but got: " + parts[3]);
        }
        // Extract the base description by removing the "(from: ... to: ...)" part
        String baseDescription = parts[2];
        if (baseDescription.contains(" (from: ")) {
            baseDescription = baseDescription.substring(0, baseDescription.indexOf(" (from: "));
        }
        Event task = new Event(baseDescription, timeParts[0], timeParts[1]);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "E | " + (this.isDone ? "1 | ": "0 | ") + this.getDescription() + " (from: " + this.from + " to: " + this.to + ") | " + this.from + "-" + this.to;
    }

    @Override
    public String getStatus() {
        return "[E]";
    }
}