package moon.models;

/**
 * Represents a match between a task and its index in the original task list.
 * <p>
 * Useful for search results where both the task itself and its position
 * in the full list are needed.
 *
 * @param index the 1-based index of the task in the original list
 * @param task  the matched {@link Task}
 */
public record TaskMatch(int index, Task task) {
    @Override
    public String toString() {
        return String.format("  %d. %s", index, task);
    }
}
