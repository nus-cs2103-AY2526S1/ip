package tsunderechan.ui;

import java.util.List;

import tsunderechan.exception.InsufficientInformationException;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;

/**
 * Represents an object that reads user input and outputs things to the screen.
 */
public class Ui {

    /**
     * Prints welcome statement when application begins.
     */
    public static String showWelcome() {
        return "Hmph! I'm Tsundere-chan.\nWhat do you want? If you ask nicely, I just MIGHT help you...";
    }

    /**
     * Prints goodbye statement when application exits.
     */
    public String showGoodbye() {
        return "Hmph, done already? D-don't talk to me anymore, you idiot!\n"
                + "It's not l-like I enjoyed talking to you or anything...";
    }

    /**
     * Returns a String when user types in "El Psy Congroo".
     * This is an Easter egg for users that recognise the characters in the profiles.
     */
    public String showElPsyCongroo() {
        return "How the heck did you come up with that? W-well, I guess it's kind of cool...";
    }

    /**
     * Returns a String when user types in "The world is ending".
     * This is an Easter egg for users that recognise the characters in the profiles.
     */
    public String showWorldIsEnding() {
        return "*rolls eyes\nDid you go to May Queen again? Enough nonsense, let's get to work.";
    }

    /**
     * Returns a String when user types in "Steins Gate".
     * This is an Easter egg for users that recognise the characters in the profiles.
     */
    public String showSteinsGate() {
        return "A-are we there yet?";
    }

    /**
     * Returns a String when user types in "i am mad scientist".
     * This is an Easter egg for users that recognise the characters in the profiles.
     */
    public String showMadScientist() {
        return "Yes, yes, I am aware. "
                + "Now if you don't mind, could you let the actual scientist here focus, please?";
    }

    /**
     * Prints a corrupted save data message.
     */
    public static void showLoadingError() throws IllegalArgumentException {
        throw new IllegalArgumentException("O-oops! The save data has been corrupted..."
                + "I guess I owe you one, so let me off this time, okay? I'll start you off with a clean list.");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     */
    public void showMarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException("You must have a task number after mark!");
    }

    /**
     * Prints a prompt to the user when marking an already marked task.
     */
    public void showAlreadyMarkedError() throws IllegalArgumentException {
        throw new IllegalArgumentException("You've already asked me to mark it, geez!");
    }

    /**
     * Prints out the newly marked task.
     *
     * @param task Task to be printed out.
     */
    public String showMarkTask(Task task) {
        return "W-well, it seems even you can get something done, I guess... "
                + "N-not like I'm impressed or anything!\n"
                + "    " + task;
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     */
    public void showUnmarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException("You must have a task number after unmark!");
    }

    /**
     * Prints a prompt to the user when unmarking an already unmarked task.
     */
    public void showAlreadyUnmarkedError() throws IllegalArgumentException {
        throw new IllegalArgumentException("It's not even done yet, geeez!");
    }

    /**
     * Prints out the newly unmarked task.
     *
     * @param task Task to be printed out.
     */
    public String showUnmarkTask(Task task) {
        return "And here I was, expecting something from you... Why do I feel disappointed?\n"
                + "    " + task;
    }

    /**
     * Throws an IllegalArgumentException when marking a task that is not in the TaskList.
     */
    public void showInvalidTaskError() throws IllegalArgumentException {
        throw new IllegalArgumentException("That's not a valid task, you iiiiidiot!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showDeleteError() throws IllegalArgumentException {
        throw new IllegalArgumentException("You must have a task number after delete!");
    }

    /**
     * Throws an IllegalArgumentException when attempting to get a task out of bounds of the TaskList.
     */
    public void showInvalidIndexError() throws IllegalArgumentException {
        throw new IllegalArgumentException("Index is out of bounds, dummy!");
    }

    /**
     * Throws an IllegalArgumentException when getIcon method doesn't return T, D or E
     */
    public void showInvalidIconError() throws IllegalArgumentException {
        throw new IllegalArgumentException("Somehow, the icon of the task isn't T, D or E...");
    }

    /**
     * Prints out the newly deleted task at specified index.
     *
     * @param task Task to be printed out.
     * @param index Index of Task to be deleted.
     */
    public String showDeleteTask(Task task, int index) {
        return "Hmph, fine. I'll remove this task, so you better be thankful.\n"
                + "    " + task
                + "\nNow you only have " + index + " tasks in your list.\n"
                + "U-um, it's not like I care or anything but, you can probably take a little break now, right?";
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showInsufficientInformationError(String task) throws InsufficientInformationException {
        throw new InsufficientInformationException("You gotta tell me the description of the " + task
                + " at least, or I can't help you, doofus!");
    }

    /**
     * Prints a prompt to the user to tell them to add a keyword when finding.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showNoKeywordDuringFind() throws InsufficientInformationException {
        throw new InsufficientInformationException("I know I'm great and all, "
                + "but how can I find something without a keyword... Are you dumb?");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showDeadlineInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include /by, dummy!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showEventInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include both /from and /to, IN THAT ORDER, dummy!");
    }

    /**
     * Prints a prompt to the user to tell them to include a keyword.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showNoKeywordError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include a keyword! "
                + "I can't read your mind, doofus!");
    }

    /**
     * Prints a prompt to the user to type something.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showNoInputError() throws InsufficientInformationException {
        throw new InsufficientInformationException("W-what? Why are you just staring at me? "
                + "I-is there something on my face?");
    }

    /**
     * Prints a prompt to the user to tell them proper input format for time and dates.
     */
    public static void showDateTimeFormatError(String context) throws IllegalArgumentException {
        throw new IllegalArgumentException("You have to write the " + context
                + " section as a valid yyyy-MM-dd HH:mm,"
                + " otherwise I can't understand it as a date and time, got it?");
    }

    /**
     * Prints a prompt to user telling them that date and time entered is invalid.
     * Used when user inputs an event with end date earlier than start time.
     */
    public static void showDateTimeInvalidError() throws IllegalArgumentException {
        throw new IllegalArgumentException("The from time must obviously be after the to time, you idiot! "
                + "Do you need to go back to grade school? Or...is time travel involved...?");
    }

    /**
     * Prints out the newly added task.
     *
     * @param task Task to be printed out.
     * @param index Index of task added.
     */
    public String showAddTask(Task task, int index) {
        return "W-well, I guess I can help you just this once. "
                + "B-but don't expect this everytime, got it?!\n"
                + "    " + task
                + "\nNow you have " + index + " tasks in your list. Better get to work!";
    }

    /**
     * Prints a prompt to the user when listing an empty list.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showNoTaskToListError() throws IllegalArgumentException {
        throw new IllegalArgumentException("What?? There's nothing to list, you idiot!");
    }

    /**
     * Prints out the list of tasks.
     *
     * @param tasks Tasks to be printed out.
     * @param index Number of tasks in the list.
     */
    public String printList(TaskList tasks, int index) {
        String result = "";
        if (index == 0) {
            this.showNoTaskToListError();
        }
        for (int i = 0; i < index; i++) {
            String output = String.format("%d.%s\n", i + 1, tasks.getTask(i));
            result = result + output;
        }
        return result;
    }

    /**
     * Prints tasks that have matching keywords in their description.
     *
     * @param matches List of Tasks that have matching keywords in their description.
     */
    public String showFindResults(List<Task> matches) {
        String result = "Why do you keep giving me so much work...\n"
                + "grumble grumble... Anyway, here's the list:\n";
        for (int i = 0; i < matches.size(); i++) {
            String output = String.format("%d.%s\n", i + 1, matches.get(i));
            result = result + output;
        }
        return result;
    }

    /**
     * Tells user that there is no task that has matching keyword.
     */
    public String showNoMatchFound() {
        return "I've looked pretty hard, but couldn't find anything... It-it's not my fault, ok?";
    }

    /**
     * Tells user that there is a duplicate task found.
     */
    public void showDuplicateTaskError(String taskString, int index) throws IllegalArgumentException {
        String s = String.format("Hey! I've already added an identical task to the list! Look!\n"
                + "%d. %s\nThere's no need to add a duplicate and make my life harder, is there?", index, taskString);
        throw new IllegalArgumentException(s);
    }
}
