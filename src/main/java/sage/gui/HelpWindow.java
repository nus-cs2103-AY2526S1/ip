package nerpbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelpWindow {
    @FXML
    private VBox helpContent;

    @FXML
    private Button closeButton;

    public void initialize() {
        // Add command sections to the help content
        addSection("General Commands",
                "help - Shows this help window",
                "bye - Exit the application");

        addSection("Task Management",
                "list - Show all tasks",
                "todo <description> - Add a new todo task",
                "deadline <description> /by <date> - Add a deadline (date format: YYYY-MM-DD)",
                "event <description> /from <start-date> /to <end-date> - Add an event");

        addSection("Task Operations",
                "mark <task-number> - Mark a task as done",
                "unmark <task-number> - Mark a task as not done",
                "delete <task-number> - Delete a task",
                "find <keyword> - Find tasks containing the keyword");
    }

    private void addSection(String title, String... items) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setStyle("-fx-padding: 5 0 0 0");

        helpContent.getChildren().add(titleLabel);

        for (String item : items) {
            Label itemLabel = new Label("• " + item);
            itemLabel.setStyle("-fx-padding: 0 0 0 15");
            itemLabel.setWrapText(true);
            helpContent.getChildren().add(itemLabel);
        }
    }

    @FXML
    private void closeHelp() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
