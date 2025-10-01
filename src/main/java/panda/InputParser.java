package panda;

import panda.action.Action;
import panda.action.AddAction;
import panda.action.DeleteAction;
import panda.action.ExitAction;
import panda.action.FindAction;
import panda.action.HelpAction;
import panda.action.ListAction;
import panda.action.MarkAction;

/**
 * InputParser will parse user input according to the different expected actions.
 */
public class InputParser {
    public InputParser() {
    }

    /**
     * Returns an Action object based on the input received from user.
     *
     * @param input Input from user.
     * @return {@link Action} subclass.
     * @throws PandaException If input is invalid.
     */
    public Action parseUserInput(String input) throws PandaException {
        String[] inputArray = input.split(" ", 2);
        String action = inputArray[0];
        if (action.equals("bye")) {
            return new ExitAction();
        } else if (action.equals("list")) {
            return new ListAction();
        } else if (action.equals("help")) {
            return new HelpAction();
        }
        if (!hasExpectedSize(inputArray, 2)) {
            throw new PandaException(action, "");
        }
        if (action.equals("mark") || action.equals("unmark")) {
            return new MarkAction(inputArray[1], action);
        }
        if (action.equals("delete")) {
            return new DeleteAction(inputArray[1]);
        }
        if (action.equals("find")) {
            return new FindAction(inputArray[1]);
        }
        if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            String[] taskInfo = inputArray[1].split(" /by | /from | /to ");
            if (action.equals("deadline") && !hasExpectedSize(taskInfo, 2)) {
                throw new PandaException(action, taskInfo[0]);
            }
            if (action.equals("event") && !hasExpectedSize(taskInfo, 3)) {
                throw new PandaException(action, taskInfo[0]);
            }
            return new AddAction(action, taskInfo);
        }
        throw new PandaException(action, "");
    }

    private boolean hasExpectedSize(String[] array, int expectedSize) {
        return array.length == expectedSize;
    }
}
