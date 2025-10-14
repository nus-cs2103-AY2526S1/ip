package lux.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores a mapping from short alias strings to full command words. Used by
 * {@link lux.parser.Parser} to expand user-entered aliases to real
 * commands.
 */
public class AliasList implements Serializable {
    private HashMap<String, String> map;

    /**
     * Initialize the alias mapping with some convenient defaults.
     */
    public AliasList() {
        map = new HashMap<>();
        this.add("ls", "list");
        this.add("exit", "bye");
        this.add("t", "todo");
        this.add("d", "deadline");
        this.add("e", "event");
    }

    public String process(String input) {
        return map.getOrDefault(input, input);
    }

    public void add(String alias, String command) {
        map.put(alias, command);
    }
}
