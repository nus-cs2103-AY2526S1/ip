# Suggested increment 1 (16-09-2025)
## AI tool used: ChatGPT
### Helped in making a command alias map under: `Parser` class
ChatGPT suggests that instead of hardcoding command aliases into the switch statement, keep a `static Map<String, String>` of all accepted aliases -> canonical command:
1. Original Code:
```java
public static Command parse(String userInput) {
    userInput = userInput.trim();
    String[] parts = userInput.split("\\s+", 2);
    String command = parts[0].toLowerCase();
    return switch (command) {
    case "bye", "end", "exit", "stop", "close" -> new ByeCommand();
    case "list", "l", "ls", "show", "display" -> new ListCommand();
    case "find", "f", "fd", "search", "lookup" -> parseFindInput(parts);
    case "mark", "m" -> parseIndexedInput(parts, "mark");
    case "unmark", "um" -> parseIndexedInput(parts, "unmark");
    case "delete", "remove" -> parseIndexedInput(parts, "delete");
    case "todo", "t" -> parseToDoInput(parts);
    case "deadline", "d" -> parseDeadlineInput(parts);
    case "event", "e" -> parseEventInput(parts);
    default -> new IncorrectCommand("Sorry! Ddeonu doesn't know what you mean ToT");
    };
}
```
2. Suggested code:
```java
private static final Map<String, String> COMMAND_ALIASES = new HashMap<>();

static {
    register("bye", "bye", "end", "exit", "stop", "close");
    register("list", "list", "l", "ls", "show", "display");
    register("find", "find", "f", "fd", "search", "lookup");
    register("mark", "mark", "m");
    register("unmark", "unmark", "um");
    register("delete", "delete", "remove", "rm", "del");
    register("todo", "todo", "t");
    register("deadline", "deadline", "d", "ddl");
    register("event", "event", "e", "schedule");
}

private static void register(String canonical, String... aliases) {
    for (String alias : aliases) {
        COMMAND_ALIASES.put(alias, canonical);
    }
}

public static Command parse(String userInput) {
    userInput = userInput.trim();
    String[] parts = userInput.split("\\s+", 2);
    String rawCommand = parts[0].toLowerCase();
    String command = COMMAND_ALIASES.getOrDefault(rawCommand, "incorrect");

    return switch (command) {
    case "bye" -> new ByeCommand();
    case "list" -> new ListCommand();
    case "find" -> parseFindInput(parts);
    case "mark" -> parseIndexedInput(parts, "mark");
    case "unmark" -> parseIndexedInput(parts, "unmark");
    case "delete" -> parseIndexedInput(parts, "delete");
    case "todo" -> parseToDoInput(parts);
    case "deadline" -> parseDeadlineInput(parts);
    case "event" -> parseEventInput(parts);
    default -> new IncorrectCommand("Sorry! Ddeonu doesn't know what you mean ToT");
    };
}
```
ChatGPT's suggestion was not taken as code readability does not increase by much. Even though it may be scalable, the chatbot does not need to use high number of aliases.  
# Suggested increment 2 (16-09-2025)
## AI tool used: ChatGPT
### Added JavaDoc for a method in `Parser` class
Suggested code:
```java
/**
 * Parses commands that require an integer index, such as "mark", "unmark", or "delete".
 *
 * @param parts       tokenized user input where {@code parts[1]} is expected to be the index
 * @param commandType the type of index-based command ("mark", "unmark", "delete")
 * @return the corresponding {@link Command} with the index applied
 * @throws SunooException if the index is missing or not a valid number
 */
private static Command parseIndexedInput(String[] parts, String commandType) {
    int taskIndex;
    try {
        taskIndex = Integer.parseInt(parts[1]);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
        throw new SunooException(ERROR_INDEX_REQUIRED);
    }
    return switch (commandType) {
        case "mark" -> new MarkCommand(taskIndex);
        case "unmark" -> new UnmarkCommand(taskIndex);
        case "delete" -> new DeleteCommand(taskIndex);
        default -> null;
    };
}
```
JavaDoc used with slight edits:
```java
/**
 * Parses commands that require an integer index, such as "mark", "unmark", or "delete".
 *
 * @param parts       User input where {@code parts[1]} is expected to be the index.
 * @param commandType The type of index-based command: "mark", "unmark", "delete".
 * @return Corresponding {@link Command} with the index applied.
 * @throws SunooException if the index is missing or not a valid number.
 */
```

