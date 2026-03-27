package bot;

import java.util.List;

import parser.AddDeadlineParser;
import parser.AddEventParser;
import parser.AddTodoParser;
import parser.DeleteItemParser;
import parser.FindItemParser;
import parser.MarkItemAsCompletedParser;
import ui.Ui;

/**
 * Main class
 */
public class Bot {
    private static final Tracker tracker = new Tracker();
    private static int totalNumBot = 0;

    /**
     * Constructor.
     * We use the same tracker instance for all bot instances
     * This is okay since we will only instantiate bot once
     * Anyway, there can only be 1 tracker instance since all tracker instances
     * are tied to the same data file
     */
    public Bot() {
        assert totalNumBot < 1;
        totalNumBot++;
    }

    /**
     * Handles the user input
     *
     * @param userInput the user's input
     * @return the bot's response
     */
    public String getResponse(String userInput) {
        String command = userInput.toLowerCase().split(" ")[0];

        try {
            if (command.equalsIgnoreCase("todo")) {
                return handleAddTodo(userInput);

            } else if (command.equalsIgnoreCase("deadline")) {
                return handleAddTodoWithDeadline(userInput);

            } else if (command.equalsIgnoreCase("event")) {
                return handleAddEvent(userInput);

            } else if (userInput.equalsIgnoreCase("list")) {
                return handleListItems();

            } else if (command.equalsIgnoreCase("mark")) {
                return handleMarkItemAsCompleted(userInput);

            } else if (command.equalsIgnoreCase("unmark")) {
                return handleUnmarkItemAsCompleted(userInput);

            } else if (command.equalsIgnoreCase("delete")) {
                return handleDeleteItem(userInput);

            } else if (command.equalsIgnoreCase("find")) {
                return handleFindItems(userInput);

            } else {
                return Ui.generateErrorMessage(new Exception("I don't recognise this command"));
            }
        } catch (java.time.format.DateTimeParseException e) {
            return Ui.generateErrorMessage(new Exception("Error parsing date: " + e.getMessage()));
        } catch (Exception e) {
            return Ui.generateErrorMessage(e);
        }
    }

    private String handleListItems() {
        return Ui.generateListTasksMessage(tracker);
    }

    private String handleAddTodo(String userInput) throws Exception {
        AddTodoParser parser = new AddTodoParser();
        parser.parse(userInput);

        Todo todo = new Todo(parser.getTodoName(), null);
        tracker.addItem(todo);

        return Ui.generateAddTaskMessage(todo, tracker);
    }

    private String handleAddTodoWithDeadline(String userInput) throws Exception {
        AddDeadlineParser parser = new AddDeadlineParser();
        parser.parse(userInput);

        Todo todo = new Todo(parser.getTodoName(), parser.getDueDate());
        tracker.addItem(todo);

        return Ui.generateAddTaskMessage(todo, tracker);
    }

    private String handleAddEvent(String userInput) throws Exception {
        AddEventParser parser = new AddEventParser();
        parser.parse(userInput);

        Event event = new Event(parser.getTodoName(), parser.getStartDate(), parser.getEndDate());
        tracker.addItem(event);

        return Ui.generateAddTaskMessage(event, tracker);
    }

    private String handleMarkItemAsCompleted(String userInput) throws Exception {
        MarkItemAsCompletedParser parser = new MarkItemAsCompletedParser();
        parser.parse(userInput);

        tracker.markItemAsCompleted(parser.getItemNumber());

        TrackerItem markedItem = tracker.getItemByNumber(parser.getItemNumber());
        return Ui.generateMarkAsCompletedMessage(markedItem);
    }

    private String handleUnmarkItemAsCompleted(String userInput) throws Exception {
        MarkItemAsCompletedParser parser = new MarkItemAsCompletedParser();
        parser.parse(userInput);

        tracker.unmarkItemAsCompleted(parser.getItemNumber());

        TrackerItem unmarkedItem = tracker.getItemByNumber(parser.getItemNumber());
        return Ui.generateUnmarkAsCompletedMessage(unmarkedItem);
    }

    private String handleDeleteItem(String userInput) throws Exception {
        DeleteItemParser parser = new DeleteItemParser();
        parser.parse(userInput);

        TrackerItem deletedItem = tracker.deleteItem(parser.getItemNumber());

        return Ui.generateDeleteTaskMessage(deletedItem, tracker);
    }

    private String handleFindItems(String userInput) throws Exception {
        FindItemParser parser = new FindItemParser();
        parser.parse(userInput);

        List<TrackerItem> matchedItems = tracker.find(parser.getItemName());

        return Ui.generateFindTasksMessage(matchedItems);
    }
}
