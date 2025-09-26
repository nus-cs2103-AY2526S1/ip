package winnie.task;

/**
 * Represents the different types of tasks.
 */
public enum TaskEnum {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String icon;

    TaskEnum(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the icon of the task type.
     *
     * @return The icon of the task type.
     */
    public String getIcon() {
        return "[" + icon + "]";
    }
}
