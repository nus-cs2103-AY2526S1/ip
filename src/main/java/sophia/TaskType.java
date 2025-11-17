package sophia;

/**
 * A list of tasks
 */
public enum TaskType {
    TODO("todo"),
    BYE("bye"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    SAVE("save"),
    FIND("find");
    public final String keyword;
    TaskType(String keyword) {
        this.keyword = keyword;
    }
}
