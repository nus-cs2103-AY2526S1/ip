package luna.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Stores an object on the hard disk.
 *
 * @param <T> Type of the object that is stored.
 */
public class Storage<T> {
    private final String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
    }

    /**
     * Saves the object onto the hard disk.
     */
    public void save(T contents) throws IOException {
        createFile();
        saveToFile(contents);
    }

    private void saveToFile(T contents) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(pathname);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(contents);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Returns the object loaded from the hard disk.
     */
    public T load() throws ClassNotFoundException, IOException {
        createFile();
        return loadFromFile();
    }

    @SuppressWarnings("unchecked")
    private T loadFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(pathname);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T object;
        try {
            object = (T) objectInputStream.readObject();
        } finally {
            objectInputStream.close();
        }
        assert object != null;
        return object;
    }

    private void createFile() throws IOException {
        File file = new File(pathname);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
}