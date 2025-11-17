package sophia;

/**
 * Parse the arguments given in fixed format
 */
public class TodoTaskParser implements Parser {
    private String argument;
    public TodoTaskParser(String argument) {
        this.argument = argument;
    }
    /**
     * parse function to parse argument and create new Task
     * @return a TodoTask by invoking TodoTask constructor
     * @throws SophiaException if the argument is blank
     */
    @Override
    public Task parse() throws SophiaException {
        if (argument.isBlank()) {
            throw new SophiaException("Usage: todo <description>");
        }
        return new TodoTask(argument.trim());
    }
}
