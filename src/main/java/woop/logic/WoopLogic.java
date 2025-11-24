package woop.logic;

import woop.ui.Ui;

/**
 * Handles main logic of Woop.
 */
public class WoopLogic {
    private static final String EXIT = "bye"; //String constant to exit loop
    private TaskList tasks;

    public WoopLogic(TaskList tasks) {
        this.tasks = tasks;
    }
    /**
     * Runs WoopLogic.
     * Exits when the constant EXIT is inputted by the user.
     */
    public String run(String userInput) {
        String command = Parser.parseCommand(userInput);
        if (checkExit(userInput)) {
            return Ui.showGoodbye();
        }
        try {
            return getWoopResponse(userInput, command);
        } catch (UnknownCommandException e) {
            return Ui.showCommandError(command);
        } catch (IllegalDescriptorException e) {
            return Ui.showDescriptorError(command);
        } catch (Exception e) {
            return Ui.showError(e);
        } finally {
            Storage.saveTasks(this.tasks);
        }
    }

    private String getWoopResponse(String userInput, String command)
            throws IllegalDescriptorException, MarkedTaskException,
            UnmarkedTaskException, UnknownCommandException {
        switch (command) { //handles all valid commands
        case "list":
            return Ui.showTaskList(this.tasks);
        case "mark":
            int listIndex = Parser.parseIndex(userInput);
            return Ui.showMarked(this.tasks.markTask(listIndex));
        case "unmark":
            listIndex = Parser.parseIndex(userInput);
            return Ui.showUnmarked(this.tasks.unmarkTask(listIndex));
        case "delete":
            listIndex = Parser.parseIndex(userInput);
            return Ui.showDeleted(this.tasks.deleteTask(listIndex));
        case "todo":
            Task t = Parser.parseDescriptor(userInput, TaskType.TODO);
            return Ui.showAddTask(this.tasks.addTask(t), this.tasks.getSize());
        case "deadline":
            t = Parser.parseDescriptor(userInput, TaskType.DEADLINE);
            return Ui.showAddTask(this.tasks.addTask(t), this.tasks.getSize());
        case "event":
            t = Parser.parseDescriptor(userInput, TaskType.EVENT);
            return Ui.showAddTask(this.tasks.addTask(t), this.tasks.getSize());
        case "find":
            String keyword = Parser.parseKeyword(userInput);
            return Ui.showFindKeyword(this.tasks.findKeyword(keyword));
        case "tag":
            int tagIdx = Parser.parseTagIndex(userInput);
            String tagDesc = Parser.parseTag(userInput);
            return Ui.showTagTask(this.tasks.tagTask(tagIdx, tagDesc));
        default:
            throw new UnknownCommandException();
        }
    }

    private boolean checkExit(String userInput) {
        return userInput.equals(EXIT);
    }
}
