package task;

import java.util.List;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String store() {

        StringBuilder sb = new StringBuilder("T" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | ");
        for(String tag : tags) {
            sb.append(tag + " / ");
        }
        return sb.append("\n").toString();

    }
}
