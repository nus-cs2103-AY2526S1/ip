public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public static Task toTask(String[] parts) throws RomidasException {
        if (parts.length != 3) {
            throw new RomidasException("Invalid number of arguments. Expected 3 but got " + parts.length);
        }
        TodoTask task = new TodoTask(parts[2]);
        if (parts[1].equals("1")) {
            task.setIsDone(true);
        }
        return task;
    }

    @Override
    public String toText() {
        return "T | " + (this.isDone ? "1 | ": "0 | ") + this.getDescription();
    }

    @Override
    public String getStatus() {
        return "[T]";
    }

}
