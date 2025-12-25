package leo;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String toSaveFormat() {
        return String.join(" | ", "T", (isDone ? "1" : "0"), description);
    }
}
