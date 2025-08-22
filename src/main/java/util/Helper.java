//helper class for useful fns
package util;

import exceptions.InvalidIndexException;

public class Helper {
    public static int parseIndex(String input, String command, int taskCount) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(input
                    .substring(command.length())
                    .trim()) - 1;
            if (index < 0 || index >= taskCount) throw new InvalidIndexException();
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }
}
