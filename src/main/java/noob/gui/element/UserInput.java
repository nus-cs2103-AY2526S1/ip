package noob.gui.element;

import javafx.scene.control.TextField;
import noob.Noob;
import noob.gui.handler.DisplayHandler;
import noob.gui.layout.DialogContainer;

/**
 * Text field for users to type commands
 */
public class UserInput extends TextField {
    private DialogContainer dialogContainer;
    private Noob noob;

    public UserInput() {}

    public UserInput(DialogContainer d, Noob n) {
        this.dialogContainer = d;
        this.noob = n;
        this.setOnAction((event -> {
            DisplayHandler.handleInput(dialogContainer, this, noob);
        }));
    }

    /**
     * Initialize fields when default constructor is called by FXML
     */
    public void initialize(DialogContainer d, Noob n) {
        this.dialogContainer = d;
        this.noob = n;

        assert dialogContainer != null : "DialogContainer cannot be null";
        assert noob != null : "Noob cannot be null";

        this.setOnAction((event -> {
            DisplayHandler.handleInput(dialogContainer, this, noob);
        }));
    }

    /**
     * Sets styling of UserInput for GUI
     *
     * @param width Preferred input width
     */
    public void setStyle(int width) {
        this.setPrefWidth(width);
    }
}
