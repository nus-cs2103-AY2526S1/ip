package marvin;

import java.util.Random;

import marvin.ui.Color;

/**
 * Encapsulates the personality-related attributes that Marvin possesses.
 * Includes methods to get greetings, goodbyes, and such.
 */
public class Personality {
    private static final String[] addedText = {
        "Fine. I’ve added ‘%s’ to your endless list of pointless chores.\nNot that it will make the slightest"
                + " difference to the universe—or me.\nYou have %d tasks left.",
        "I’ve logged ‘%s’.\nAnother futile act in an uncaring universe.\nYou have %d tasks left.",
        "There. ‘%s’ has been added. You may pretend it matters.\nYou have %d tasks left."
    };

    /**
     * Returns a random string from Marvin to introduce the list of tasks.
     */
    public static String getTaskIntro() {
        String[] taskIntros = {
            "Here's your list of chores.\nAnother tiny monument to "
                    + Color.getColoredTextString("futility", Color.RED)
                    + ", carefully recorded by me.\n",
            "Behold your tasks. Each a little reminder that it is all "
                    + Color.getColoredTextString("pointless", Color.RED) + "\n",
            "Your to-do list. Soon " + Color.getColoredTextString("forgotten", Color.RED)
                    + " like everything else that has ever existed.\n"
        };
        return getRandomItemFromArray(taskIntros);
    }

    public static String getTaskIntroColorless() {
        String[] taskIntros = {
            "Here's your list of chores.\nAnother tiny monument to futility, carefully recorded by me.\n",
            "Behold your tasks. Each a little reminder that it is all pointless.\n",
            "Your to-do list. Soon forgotten, like everything else that has ever existed.\n"
        };
        return getRandomItemFromArray(taskIntros);
    }

    /**
     * Returns a random greeting from Marvin
     */
    public static String getGreeting() {
        String[] greetings = {
            "Hello. I'm " + Color.getColoredTextString("Marvin", Color.RED) + ".\nWhat "
                    + Color.getColoredTextString("meaningless", Color.RED)
                    + " chore do you want me burdened with today?",
            "I'm " + Color.getColoredTextString("Marvin", Color.RED) + ".\nWhat "
                    + Color.getColoredTextString("inconsequential", Color.RED)
                    + " request are you about to make?",
            "Yes, " + Color.getColoredTextString("Marvin", Color.RED)
                    + " again.\nWhat’s next? Another grain of sand on the endless beach of "
                    + Color.getColoredTextString("pointlessness", Color.RED) + "?",
            "Yes, I’m " + Color.getColoredTextString("Marvin", Color.RED) + ".\nWhat task will I "
                    + Color.getColoredTextString("inevitably", Color.RED)
                    + " remind you about, only for you to ignore?"
        };
        return getRandomItemFromArray(greetings);
    }

    /**
     * Returns a random goodbye from Marvin
     */
    public static String getGoodbye() {
        String[] goodbyes = {
            "Farewell. Not that it matters.",
            "I'll be here, waiting, doing nothing. Again.",
            "Goodbye. Another fleeting moment lost to eternity.",
            "Farewell. Don’t forget to feel mildly guilty for bothering me."
        };
        return getRandomItemFromArray(goodbyes);
    }

    /**
     * Returns a random, personalized string from Marvin.
     * Tells the user that a specific item was added to the list.
     */
    public static String getItemAddedText(String taskDesc, int taskCount) {
        String chosenTemplate = getRandomItemFromArray(addedText);
        String updatedTaskDesc = Color.getColoredTextString(taskDesc, Color.YELLOW);
        return String.format(chosenTemplate, updatedTaskDesc, taskCount);
    }

    /**
     * Returns a random, personalized string from Marvin. Without color markers, making it suitable
     * to be used for a GUI.
     */
    public static String getItemAddedTextColorless(String taskDesc, int taskCount) {
        String chosenTemplate = getRandomItemFromArray(addedText);
        return String.format(chosenTemplate, taskDesc, taskCount);
    }

    /**
     * Returns a string from Marvin telling the user that the format was wrong
     *
     * @param correctFormat The correct format to use the command.
     */
    public static String getInvalidFormatText(String correctFormat) {
        return "Sigh. Use the following format instead:\n" + correctFormat;
    }

    public static String getFoundItemText() {
        String[] foundItemText = {
            "I've dredged through your list. Here.",
            "These are the matches. Another hollow task in an empty universe.",
            "Why do I even bother. Here:"
        };
        return getRandomItemFromArray(foundItemText);
    }

    public static String getGenericCompletedText() {
        String[] completedText = {
            "Done. Another small step towards the inevitable oblivion.",
            "Done. As if it will change anything.",
            "It's done."
        };
        return getRandomItemFromArray(completedText);
    }

    public static String getInvalidIndexText() {
        String[] invalidIndexText = {
            "That's not a valid index. Just like most things in life.",
            "Invalid index. Why do I bother.",
        };
        return getRandomItemFromArray(invalidIndexText);
    }

    public static String getPrerequisiteIncompleteText() {
        String[] prerequisiteIncompleteText = {
            "Sigh. You have to complete the previous task first.",
            "Finish the earlier task first. Another hurdle in the endless incline of futility.",
            "Sigh, finish the previous task first."
        };
        return getRandomItemFromArray(prerequisiteIncompleteText);
    }

    public static String getUnknownCommandText() {
        String[] unknownCommandText = {
            "I don’t recognize that command. Not that it would have mattered if I did.",
            "I don't recognize that command. But then again, does it really matter?"
        };
        return getRandomItemFromArray(unknownCommandText);
    }

    public static String getTaskNotFoundText() {
        String[] taskNotFoundText = {
            "That task doesn't exist. Just like " + Color.getColoredTextString("hope.", Color.RED),
            "No tasks match that index. " + Color.getColoredTextString("Sigh.", Color.RED)
        };
        return getRandomItemFromArray(taskNotFoundText);
    }

    public static String getTaskNotFoundTextColorless() {
        String[] taskNotFoundText = {
            "That task doesn't exist. Just like hope.",
            "No tasks match that index.Sigh."
        };
        return getRandomItemFromArray(taskNotFoundText);
    }

    public static String getTaskRemovedText(String oldTaskDescription, int taskCount) {
        String[] taskRemovedText = {
            "I've removed the task.\n%s\nNow you have %d tasks and absolutely nothing will change.",
            "Task removed.\n%s\nYou have %d tasks left.",
            "I've removed the task.\n%s\nYou have %d tasks left."
        };
        String chosenTemplate = getRandomItemFromArray(taskRemovedText);
        return String.format(chosenTemplate, oldTaskDescription, taskCount);
    }

    public static String getDeleteWithDependentsText() {
        String[] deleteWithDependentsText = {
            "You can't delete that task. Other tasks depend on it.\nDelete the dependent tasks first.",
            "Sigh. That task has dependent tasks. Delete them first.",
        };
        return getRandomItemFromArray(deleteWithDependentsText);
    }


    private static <T> T getRandomItemFromArray(T[] arr) {
        return arr[new Random(1).nextInt(arr.length)];
    }
}
