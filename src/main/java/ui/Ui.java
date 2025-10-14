package ui;

import java.util.List;

import bot.Tracker;
import bot.TrackerItem;

public class Ui {

    /**
     * generates the error response message from the error
     *
     * @param error the error
     */
    public static String generateErrorMessage(Exception error) {
        return "Oops, there was an error with your command: " + error.getMessage();
    }

    /**
     * generates the response message after a task has been added to the tracker
     *
     * @param item    the item added
     * @param tracker the bot's tracker
     */
    public static String generateAddTaskMessage(TrackerItem item, Tracker tracker) {
        return "Got it. I've added this task:" + "\n"
                + item + "\n"
                + "Now you have " + tracker.getItemCount() + " item(s) in the tracker";
    }

    /**
     * generates the response message after a task has been deleted from the tracker
     *
     * @param deletedItem the item added
     * @param tracker     the bot's tracker
     */
    public static String generateDeleteTaskMessage(TrackerItem deletedItem, Tracker tracker) {
        return "Noted. I've removed this task:" + "\n"
                + deletedItem + "\n"
                + "Now you have " + tracker.getItemCount() + " item(s) in the tracker";
    }

    /**
     * generates the response message after a task has been marked as completed
     *
     * @param markedItem the item added
     */
    public static String generateMarkAsCompletedMessage(TrackerItem markedItem) {
        return "OK, I've marked this item as done:" + "\n"
                + markedItem;
    }

    /**
     * generates the response message after a task has been unmarked as completed
     *
     * @param unmarkedItem the item added
     */
    public static String generateUnmarkAsCompletedMessage(TrackerItem unmarkedItem) {
        return "OK, I've marked this item as not done yet:" + "\n"
                + unmarkedItem;
    }

    /**
     * generates the response message when returning a list of all tasks
     *
     * @param tracker the tracker
     */
    public static String generateListTasksMessage(Tracker tracker) {
        return "Sure! Here are the items in the tracker: " + "\n" + tracker.toString();
    }

    /**
     * generates the response message when returning a filtered list of tasks
     *
     * @param matchedItems the items
     */
    public static String generateFindTasksMessage(List<TrackerItem> matchedItems) {
        if (matchedItems.isEmpty()) {
            return "No items match the given query";
        }

        StringBuilder display = new StringBuilder();
        for (int i = 0; i < matchedItems.size(); i++) {
            TrackerItem item = matchedItems.get(i);
            String itemString = (i + 1) + ". " + item.toString() + "\n";
            display.append(itemString);
        }

        return "Here are the matching items in your tracker:" + "\n"
                + display;
    }
}

