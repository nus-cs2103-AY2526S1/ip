package noob.gui.element;

import javafx.scene.control.Button;
import noob.Noob;
import noob.gui.handler.DisplayHandler;
import noob.gui.layout.DialogContainer;

/**
 * Button to send user input to the chatbot
 */
public class SendButton extends Button {
    private DialogContainer dialogContainer;
    private UserInput userInput;
    private Noob noob;

    public SendButton() {
        super("Send");
    }

    public SendButton(String label, DialogContainer d, UserInput u, Noob n) {
        super(label);
        this.dialogContainer = d;
        this.userInput = u;
        this.noob = n;
        this.setOnMouseClicked((event -> {
            DisplayHandler.handleInput(dialogContainer, userInput, noob);
        }));
    }

    /**
     * Initialize fields when default constructor is called by FXML
     */
    public void initialize(DialogContainer d, UserInput u, Noob n) {
        this.dialogContainer = d;
        this.userInput = u;
        this.noob = n;

        assert dialogContainer != null : "DialogContainer cannot be null";
        assert userInput != null : "UserInput cannot be null";
        assert noob != null : "Noob cannot be null";

        this.setOnMouseClicked((event -> {
            DisplayHandler.handleInput(dialogContainer, userInput, noob);
        }));
    }

    /**
     * Sets styling of SendButton for GUI
     *
     * @param width Preferred button width
     */
    public void setStyle(int width) {
        this.setPrefWidth(width);
    }
}
