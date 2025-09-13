package bobbywasabi.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bobbywasabi.client.Client;
import bobbywasabi.client.ClientList;
import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.ToDo;

class StorageTest {

    private Storage storage;
    private File tempFolder;
    private File taskFile;
    private File clientFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFolder = Files.createTempDirectory("bobbywasabi_test").toFile();
        taskFile = new File(tempFolder, "tasks.txt");
        clientFile = new File(tempFolder, "clients.txt");

        storage = new Storage(tempFolder.getAbsolutePath(),
                taskFile.getAbsolutePath(),
                clientFile.getAbsolutePath());
    }

    @Test
    void testCreateDataStorage_createsFilesAndFolder_success() throws BobbyWasabiException {
        storage.createDataStorage();
        assertTrue(tempFolder.exists() && tempFolder.isDirectory());
        assertTrue(taskFile.exists() && taskFile.isFile());
        assertTrue(clientFile.exists() && clientFile.isFile());
    }

    @Test
    void testClientParser_validLine_success() throws BobbyWasabiException {
        String line = "John Doe|91234567|30|Engineer|LifeInsurance";
        Client client = storage.clientParser(line);
        assertEquals("John Doe", client.getName());
        assertEquals("91234567", client.getContactNumber());
        assertEquals(30, client.getAge());
        assertEquals("Engineer", client.getOccupation());
        assertEquals("LifeInsurance", client.getCurrentPolicies());
    }

    @Test
    void testClientParser_invalidAge_throwsException() {
        String line = "John Doe|91234567|abc|Engineer|LifeInsurance";
        assertThrows(BobbyWasabiException.class, () -> storage.clientParser(line));
    }

    @Test
    void testTaskParser_todoTask_success() throws BobbyWasabiException {
        String line = "T|Read book|[ ]";
        Task task = storage.taskParser(line);
        assertTrue(task instanceof ToDo);
        assertEquals("Read book", task.getDescription());
        assertEquals("[ ]", task.getMarkedStatus());
    }

    @Test
    void testFileWrite_andLoadClientList_success() throws BobbyWasabiException, IOException {
        storage.createDataStorage();

        Client firstClient = new Client("Jane Doe", "98765432", 28, "Designer", "HealthInsurance");
        Client secondClient = new Client("John Doe", "9876543", 22, "Designer", "HealthInsurance");
        storage.fileWrite(firstClient.getData(), Storage.StorageType.CLIENTLIST);
        storage.fileWrite(secondClient.getData(), Storage.StorageType.CLIENTLIST);

        ClientList clients = storage.loadClientList();
        assertEquals(2, clients.size());
        Client firstLoad = clients.get(0);
        assertEquals("Jane Doe", firstLoad.getName());
        assertEquals("98765432", firstLoad.getContactNumber());
        assertEquals(28, firstLoad.getAge());
        Client secondLoad = clients.get(1);
        assertEquals("John Doe", secondLoad.getName());
        assertEquals("9876543", secondLoad.getContactNumber());
        assertEquals(22, secondLoad.getAge());
    }

}
