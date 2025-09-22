package sofi;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String tagsString = getTagsString();
        return "[T]" + getStatusIcon() + " " + description + 
               (tagsString.isEmpty() ? "" : " " + tagsString);
    }
}