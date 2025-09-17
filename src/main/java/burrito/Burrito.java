package burrito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import burrito.tasklist.Task;
import burrito.tasklist.TaskList;
import burrito.ui.TextUI;
import burrito.parser.Parser;
import burrito.storage.Storage;

public class Burrito {

    private final List<Task> cache;
    private final TextUI textUI;
    private final Storage storage;
    private final Parser parser;
    private final Map<String, CommandHandler> commandHandlers;

    public Burrito() {
        this.textUI = new TextUI();
        this.storage = new Storage();
        this.parser = new Parser();
        this.cache = storage.initializeCache();

        // Register all commands once
        this.commandHandlers = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        commandHandlers.put("list", args -> TextUI.list(cache));
        commandHandlers.put("mark", args -> TaskList.mark(cache, args));
        commandHandlers.put("unmark", args -> TaskList.unmark(cache, args));
        commandHandlers.put("todo", args -> TaskList.todo(cache, args));
        commandHandlers.put("deadline", args -> TaskList.deadline(cache, args));
        commandHandlers.put("event", args -> TaskList.event(cache, args));
        commandHandlers.put("delete", args -> TaskList.delete(cache, args));
        commandHandlers.put("find", args -> TaskList.find(cache, args));
        commandHandlers.put("bye", args -> TextUI.bye());
        commandHandlers.put("help", args -> TextUI.help());
    }

    public String getResponse(String input) {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];
        String[] args = inputArr;

        CommandHandler handler = commandHandlers.get(command);
        if (handler != null) {
            return handler.execute(args);
        }
        return "Not a valid command!";
    }

    public static void main(String[] args) {
        Burrito burrito = new Burrito();
        System.out.println(burrito.getResponse("help"));
    }

    @FunctionalInterface
    interface CommandHandler {
        String execute(String[] args);
    }
}
