public class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(TaskType type,String description) {
        this.type = type;
        this.description =description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public String toString() {
        String typeIcon = switch (type) {
            case TODO -> "T";
            case DEADLINE -> "D";
            case EVENT -> "E";
        };
        return "[" + typeIcon + "]" + "[" + this.getStatusIcon() +"] " + description;
    }

    public String toFormat() {
        String isDoneNumber = isDone ? "1" : "0";
        switch (type) {
        case TODO :
            return "T " + "| "+ isDoneNumber +" | "+ description;
        case DEADLINE:
            String by = ((Deadline)this).by;
            return "D " + "| "+ isDoneNumber +" | " +description +" | " +by;
        case EVENT:
            String from = ((Event)this).from;
            String to = ((Event)this).to;
            return "E " + "| " + isDoneNumber + " | "+description+" | "+from+" | " + to;
        default:
            return "";
        }

    }
}
