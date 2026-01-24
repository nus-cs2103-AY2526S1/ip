package noob.gui.handler;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import noob.Noob;
import noob.gui.element.DialogBox;
import noob.gui.element.UserInput;
import noob.gui.image.NoobImage;
import noob.gui.image.UserImage;
import noob.gui.layout.DialogContainer;

/**
 * Handles text display in GUI
 */
public class DisplayHandler {
    public static final String EXIT_COMMAND = "_EXIT_";

    /**
     * Handles user input commands and displays the appropriate dialog boxes
     *
     * @param dialogContainer Container to display dialog boxes in
     * @param userInput Text field input that users type commands in
     * @param noob NoobBot instance
     */
    public static void handleInput(DialogContainer dialogContainer, UserInput userInput, Noob noob) {
        String noobResponse = noob.getResponse(userInput.getText());
        String noobMsgDisplay = noobResponse;

        if (isExit(noobResponse)) {
            noobMsgDisplay = noob.exitGui();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(userInput.getText(), new UserImage()),
                DialogBox.getNoobDialogBox(noobMsgDisplay, new NoobImage()));
        userInput.clear();

        // Exits after one second delay
        if (isExit(noobResponse)) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 1000);
        }
    }

    /**
     * Displays greeting dialog box upon GUI launch
     *
     * @param dialogContainer Container to display dialog box in
     * @param noob NoobBot instance
     */
    public static void displayGreeting(DialogContainer dialogContainer, Noob noob) {
        DialogBox greetingDialogBox = DialogBox.getNoobDialogBox(noob.greetGui(), new NoobImage());
        dialogContainer.getChildren().addAll(greetingDialogBox);
    }

    /**
     * Checks if a command executed is an exit command
     *
     * @param msg String returned from command.execute
     * @return True if the returned string from executing the command is equal to EXIT_COMMAND
     */
    private static boolean isExit(String msg) {
        return msg.equals(EXIT_COMMAND);
    }
}
