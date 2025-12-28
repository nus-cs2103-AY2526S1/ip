package logic.commands;

/**
 * Represents the type of command that can be executed
 */
public enum CommandType {
    EXIT("bye"),
    LIST("list"),
    DELETE("delete "),
    MARK("mark "),
    UNMARK("unmark "),
    ADD_TODO("todo "),
    ADD_DEADLINE("deadline "),
    ADD_EVENT("event "),
    FIND("find ");

    private String inputToMatch;

    CommandType(String inputToMatch) {
        this.inputToMatch = inputToMatch;
    }

    public String getInputToMatch() {
        return this.inputToMatch;
    }

}
