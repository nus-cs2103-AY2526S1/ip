package chatbot.storage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import chatbot.client.Client;

/**
 * ClientStorage handles storage of client information for B33PBOP.
 * On first usage, it creates a new DIRECTORY with a file b33pbop-clients.txt which stores client info in String format.
 * Subsequent usage overwrites the existing file to update ClientStorage.
 */
public class ClientStorage {
    private final File storageFile;

    /**
     * Initializes DIRECTORY and storageFile variables.
     * Creates a new directory and file if they do not exist.
     *
     * @throws IOException If storage creation fails.
     */
    public ClientStorage() throws IOException {
        File directory = new File("data");
        directory.mkdirs();

        storageFile = new File(directory, "clients.txt");
        if (!storageFile.exists() && !storageFile.createNewFile()) {
            throw new IOException("Failed to create storage file");
        }
    }

    public File getStorageFile() {
        return this.storageFile;
    }

    /**
     * Updates clients.txt file with a new client list.
     *
     * @param clients List of tasks added by the user.
     * @throws IOException If update fails.
     */
    public void updateStorage(List<Client> clients) throws IOException {
        try (PrintWriter pw = new PrintWriter(this.storageFile)) {
            for (Client c : clients) {
                pw.println(c.toSaveFormat());
            }
        }
    }
}
