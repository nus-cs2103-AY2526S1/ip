package thecoolerduke.main;

import thecoolerduke.exceptions.InvalidFormatException;

/**
 * Task priority is split into 3 different levels.
 */
public enum Priority {
    HIGH(3, "HIGH"),
    MEDIUM(2, "MEDIUM"),
    LOW(1, "LOW");

    private final int level;
    private final String label;

    Priority(int level, String label) {
        this.level = level;
        this.label = label;
    }

    /**
     * Returns the priority level as an integer.
     *
     * @return Priority level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the string label for the given priority.
     *
     * @return String label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Maps integer input to Priority enum
     */
    public static Priority fromString(String level) throws InvalidFormatException {
        try {
            int integerLevel = Integer.parseInt(level);

            for (Priority p : Priority.values()) {
                if (p.getLevel() == integerLevel) {
                    return p;
                }
            }

            //throws exception if not within range of Priority values
            throw new InvalidFormatException("Invalid priority level: Not in range of values 1,2,3!");

        } catch (NumberFormatException e) {
            //throws exception if not an integer
            throw new InvalidFormatException("Invalid priority level: Not a number!");
        }


    }
}


