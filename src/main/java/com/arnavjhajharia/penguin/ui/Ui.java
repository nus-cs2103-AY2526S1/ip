package com.arnavjhajharia.penguin.ui;

public interface Ui {


    /**
     * Displays the introduction banner or startup message.
     * Called once at the beginning of the application.
     */
    void showIntro();

    /**
     * Displays a visual divider (e.g., a line of dashes).
     * Typically used before and after command output
     * to make the UI clearer.
     */
    void showDivider();


    /**
     * Displays normal output text to the user.
     *
     * @param text the message content to show
     */
    void showText(String text);

    /**
     * Displays an error message to the user.
     * Implementations may style or highlight this differently
     * from normal text (e.g., red in a console, popup in a GUI).
     *
     * @param errorText the error message content to show
     */
    void showError(String errorText);


    /**
     * Reads one line of user input.
     * <p>
     * This method should block until input is available,
     * or return {@code null} if input is closed (e.g., EOF or
     * the GUI window is closed).
     * </p>
     *
     * @return the input string, or {@code null} if no more input is available
     */
    String readLine();

    /**
     * Displays the exit message or shutdown banner.
     * Called once before the application ends.
     */
    void showExit();
}
