package conversal.task;

/**
 * Enumerates the supported task types in Conversal.
 * Each task type has an associated symbol used in the UI and storage.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]"),
    DOWITHIN("[W]");

    private final String symbol;

    /**
     * Constructs a {@code TaskType} with the given symbol.
     *
     * @param symbol short string representation of the task type
     */
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol of this task type.
     *
     * @return the string symbol
     */
    public String getSymbol() {
        return symbol;
    }
}
