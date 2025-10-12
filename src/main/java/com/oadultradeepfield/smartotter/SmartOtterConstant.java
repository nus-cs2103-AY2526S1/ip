package com.oadultradeepfield.smartotter;

/**
 * Constants used throughout the SmartOtter application. This class cannot be instantiated.
 */
public final class SmartOtterConstant {
    public static final String APP_NAME = "SmartOtter 🦦📚";
    public static final String HORIZONTAL_BAR =
        "____________________________________________________________";

    /**
     * Welcome message displayed when the application starts
     */
    public static final String GREETING_MESSAGE_TEMPLATE =
        """
            Hello! I'm %s
            I can help you with anything,
            as long as you give me fish 😋"""
            .formatted(APP_NAME);

    /**
     * Message displayed when user exits the application
     */
    public static final String FAREWELL_MESSAGE = "Bye. Hope to eat more fish soon!";

    /**
     * Path for saving the list of tasks as a text file
     */
    public static final String SAVE_PATH = "./data/smart_otter.txt";

    /**
     * Default maximum Levenshtein distance for fuzzy string matching.
     * Allows up to 2 character differences (typos, insertions, deletions).
     */
    public static final int DEFAULT_FUZZY_MATCH_DISTANCE = 2;

    // Prevents instantiation
    private SmartOtterConstant() {
    }
}
