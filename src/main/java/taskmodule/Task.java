package taskmodule;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String note;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.note = null;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    public Task addNote(String noteContent) {
        this.note = noteContent;
        return this;
    }

    public String getNote() {
        return this.note;
    }

    @Override
    public String toString() {
        String stringWithoutNote = "[" + this.getStatusIcon() + "] " + this.description;

        if (this.note == null || this.note.isEmpty()) {
            return stringWithoutNote;
        }
        return stringWithoutNote + " {note: " + this.note + "}";
    }

    public String toDataString() {
        int isDoneStatus = this.isDone ? 1 : 0;
        String noteContent = (this.note == null) ? "" : this.note;

        return String.format("%d | %s | %s", isDoneStatus, this.description, noteContent);
    }
}
