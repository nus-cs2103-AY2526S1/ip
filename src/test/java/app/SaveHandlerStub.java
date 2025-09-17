package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A stub implementation of SaveHandler for testing purposes.
 * Stores data in memory instead of writing to the filesystem.
 */
public class SaveHandlerStub extends SaveHandler {

    private List<String> saveData;

    /**
     * Initializes the stub with an empty in-memory save list.
     */
    public SaveHandlerStub() {
        super("");
        saveData = new ArrayList<>();
    }

    @Override
    public void save(String[] saveStrings) {
        saveData.addAll(Arrays.asList(saveStrings));
    }

    @Override
    public String[] load() {
        return saveData.toArray(String[]::new);
    }
}
