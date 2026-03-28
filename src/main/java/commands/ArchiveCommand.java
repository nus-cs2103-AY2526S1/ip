package commands;

import java.io.File;
import java.io.FileWriter;

import taskstuff.TaskList;
import ui.Ui;
import storage.Storage;

public class ArchiveCommand extends Command {
    private final String ARCHIVE_FILE = "archive.txt";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try (FileWriter writer = new FileWriter(ARCHIVE_FILE, true)) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(System.lineSeparator());
                writer.write(taskList.get(i).toString() + System.lineSeparator());
            }
            taskList.clear(); // Clear the current task list
            storage.save(taskList); // Save the now-empty list
            return ui.showArchive(ARCHIVE_FILE);
        } catch (Exception e) {
            return ui.showError("Failed to archive tasks: " + e.getMessage());
        }
    }
}