package mel.apps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mel.exceptions.MelException;

public class StorageStub extends Storage {

    List<String> saved;

    public StorageStub() {
        super("");
        saved = new ArrayList<>();

    }

    @Override
    public void save(String[] stringsToSave) {
        saved.addAll(Arrays.asList(stringsToSave));

    }

    @Override
    public String[] load() {
        return saved.toArray(String[]::new);
    }


}
