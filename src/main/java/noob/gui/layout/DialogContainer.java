package noob.gui.layout;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Container for DialogBox
 */
public class DialogContainer extends VBox {

    /**
     * Sets style of DialogContainer for GUI
     *
     * @param scrollPane ScrollPane containing the DialogContainer
     */
    public void setStyle(ScrollPane scrollPane) {
        this.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }
}
