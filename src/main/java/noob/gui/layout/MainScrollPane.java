package noob.gui.layout;

import javafx.scene.control.ScrollPane;

/**
 * Main scroll pane container for GUI
 */
public class MainScrollPane extends ScrollPane {

    /**
     * Sets the styling of the scroll pane for GUI
     *
     * @param width Preferred scroll pane width
     * @param height Preferred scroll pane height
     */
    public void setStyle(int width, int height) {
        this.setPrefSize(width, height);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setFitToWidth(true);
    }
}
