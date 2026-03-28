package alex;

import java.util.HashMap;

/**
 * Represents a collection of user-defined aliases for chatbot commands.
 * An <code>Alias</code> object maps a <code>CommandType</code> to a single
 * alias string, allowing commands to be executed using shorter or custom keywords.
 */
public class Alias {

    private HashMap<CommandType, String> aliasMap;

    /**
     * Constructs an <code>Alias</code> object with a predefined alias map.
     *
     * @param aliasMap Map containing command-to-alias mappings.
     */
    public Alias(HashMap<CommandType, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Constructs an empty <code>Alias</code> object with no mappings.
     */
    public Alias() {
        this.aliasMap = null;
    }

    /**
     * Sets or updates the alias for the specified command.
     *
     * @param command Command type to assign an alias to.
     * @param alias   Alias string for the command.
     */
    public void setAlias(CommandType command, String alias) {
        aliasMap.put(command, alias);
    }

    /**
     * Retrieves the alias assigned to the specified command.
     *
     * @param command Command type whose alias is requested.
     * @return Alias string if present; <code>null</code> if no alias is assigned.
     */
    public String getAlias(CommandType command) {
        return aliasMap.get(command);
    }

    /**
     * Returns a formatted list of all command aliases.
     * Each command is displayed as <code>keyword: alias</code> on a new line.
     *
     * @return String representation of all aliases.
     */
    public String listOfAliases() {
        StringBuilder sb = new StringBuilder();
        for (CommandType command : aliasMap.keySet()) {
            String alias = aliasMap.get(command);
            if (alias != null) {
                sb.append(command.getKeyword())
                        .append(": ")
                        .append(alias)
                        .append("\n");
            }
        }
        return sb.toString().trim();
    }
}
