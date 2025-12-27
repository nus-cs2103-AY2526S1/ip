package fatty.task;

/**
 * To do includes description only
 */
public class ToDoTask extends Task {
    private static final String type = "[T]";

    public ToDoTask(String description){
        super(description);
  }

    @Override
    public String toString(){
        return type + super.toString();
    }

    /**
     * Reformat Task into "type | status | description | " to save into local file.
     * @return
     */
    @Override
    public String toDataString() {
        return "T | " + (this.isMark ? "1" : "0") + " | " + this.description;
    }
}
