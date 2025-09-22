package izayoi;

import java.util.List;

/**
 * Represents a class that can have its contents recreated from sequential user commands
 */
public interface Commandifiable {

    /**
     * Returns a list of strings required to recreate the same object from user commands
     * @return a list of commands that, when entered, creates the same task in its current state
     */
    public List<String> commandify();
}
