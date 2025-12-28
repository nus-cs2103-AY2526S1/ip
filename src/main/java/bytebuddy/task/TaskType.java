package bytebuddy.task;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");
    private final String symbol;
    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol representing the type of this task.
     * <p>
     * Each {@link TaskType} has a short symbol used for display or storage:
     * <ul>
     *     <li>{@link #TODO} → "T"</li>
     *     <li>{@link #DEADLINE} → "D"</li>
     *     <li>{@link #EVENT} → "E"</li>
     * </ul>
     *
     * @return the string symbol associated with this {@link TaskType}
     */
    public String getSymbol() {
        return symbol;
    }
}
