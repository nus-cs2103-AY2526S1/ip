package bobbywasabi;

import bobbywasabi.BobbyWasabi;
import bobbywasabi.client.Client;
import bobbywasabi.client.ClientList;
import bobbywasabi.storage.Storage;
import bobbywasabi.tasks.*;
import bobbywasabi.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BobbyWasabiTest {

    private BobbyWasabi bobby;

    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    @BeforeEach
    void setUp() throws Exception {
        Files.deleteIfExists(Paths.get("./testdata/BobbyWasabiTasks.txt"));
        Files.deleteIfExists(Paths.get("./testdata/BobbyWasabiClients.txt"));
        Files.createDirectories(Paths.get("./testdata"));

        bobby = new BobbyWasabi();
        setPrivateField(bobby, "storage", new Storage("./testdata", "./testdata/BobbyWasabiTasks.txt", "./testdata/BobbyWasabiClients.txt"));
        setPrivateField(bobby, "taskList", new TaskList());
        setPrivateField(bobby, "clientList", new ClientList());

        bobby.processTodoCommand("todo Read book");
        bobby.processDeadlineCommand("deadline Submit report /by 2099-12-31T23:59");
        bobby.processAddClient("addclient Alice,123,30,Engineer,PolicyA");
    }

    @Test
    void testProcessMarkCommand_valid() {
        String result = bobby.processMarkCommand("mark,1");
        assertTrue(bobby.processListCommand().contains("[X] Read book"));
        assertTrue(result.toLowerCase().contains("mark"));
    }

    @Test
    void testProcessMarkCommand_invalidIndex() {
        String result = bobby.processMarkCommand("mark,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessUnmarkCommand_valid() {
        bobby.processMarkCommand("mark,1");
        String result = bobby.processUnmarkCommand("unmark,1");
        assertTrue(bobby.processListCommand().contains("[ ] Read book"));
        assertTrue(result.toLowerCase().contains("not done yet"));
    }

    @Test
    void testProcessTodoCommand_valid() {
        String result = bobby.processTodoCommand("todo Buy milk");
        assertTrue(bobby.processListCommand().contains("Buy milk"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessTodoCommand_invalid() {
        String result = bobby.processTodoCommand("todo ");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeadlineCommand_valid() {
        String result = bobby.processDeadlineCommand("deadline Finish project,31/12/2099 2359");
        assertTrue(bobby.processListCommand().contains("Finish project"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessDeadlineCommand_invalid() {
        String result = bobby.processDeadlineCommand("deadline /by ");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessEventCommand_valid() {
        String result = bobby.processEventCommand("event Meeting,10am,11am");
        assertTrue(bobby.processListCommand().contains("Meeting"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessEventCommand_invalid() {
        String result = bobby.processEventCommand("event Meeting");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeleteCommand_valid() {
        int before = bobby.processListCommand().split("\n").length;
        String result = bobby.processDeleteCommand("delete,1");
        int after = bobby.processListCommand().split("\n").length;
        assertTrue(result.toLowerCase().contains("removed"));
        assertEquals(before - 1, after);
    }

    @Test
    void testProcessDeleteCommand_invalid() {
        String result = bobby.processDeleteCommand("delete,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessFindCommand_valid() {
        String result = bobby.processFindCommand("find,book");
        assertTrue(result.toLowerCase().contains("read book"));
    }

    @Test
    void testProcessFindCommand_invalid() {
        String result = bobby.processFindCommand("find,");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessEditClient_valid() {
        String result = bobby.processEditClient("editclient 1,name,Bob");
        assertTrue(result.toLowerCase().contains("updated"));
        assertTrue(bobby.processClientsCommand().contains("Bob"));
    }

    @Test
    void testProcessEditClient_invalid() {
        String result = bobby.processEditClient("editclient 100,name,Bob");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeleteClient_valid() {
        int before = bobby.processClientsCommand().split("\n").length;
        String result = bobby.processDeleteClient("deleteclient,1");
        int after = bobby.processClientsCommand().split("\n").length;
        assertTrue(result.toLowerCase().contains("removed this client"));
        assertEquals(before - 6, after);
    }

    @Test
    void testProcessDeleteClient_invalid() {
        String result = bobby.processDeleteClient("deleteclient,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessAddClient_valid() {
        String result = bobby.processAddClient("addclient John,456,25,Doctor,PolicyB");
        assertTrue(result.toLowerCase().contains("add"));
        assertTrue(bobby.processClientsCommand().contains("John"));
    }

    @Test
    void testProcessAddClient_invalid() {
        String result = bobby.processAddClient("addclient ");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessClientsCommand() {
        String result = bobby.processClientsCommand();
        assertTrue(result.contains("Alice"));
    }

    @Test
    void testProcessByeCommand() {
        String result = bobby.processByeCommand();
        assertTrue(result.toLowerCase().contains("bye"));
    }

    @Test
    void testProcessListCommand() {
        String result = bobby.processListCommand();
        assertTrue(result.contains("Read book"));
    }

    @Test
    void testProcessDefaultCommand() {
        String result = bobby.processDefaultCommand();
        assertTrue(result.toLowerCase().contains("oops"));
    }

    /*
    @Test
    void testGetResponse_allCommands() {
        assertTrue(bobby.getResponse("bye").toLowerCase().contains("bye"));
        assertTrue(bobby.getResponse("list").contains("Read book"));
        assertTrue(bobby.getResponse("mark,1").toLowerCase().contains("mark"));
        assertTrue(bobby.getResponse("unmark,1").toLowerCase().contains("not done yet"));
        assertTrue(bobby.getResponse("todo, Buy eggs").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("deadline , Do,31/12/2099 2359").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("event Party,1pm,2pm").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("delete,1").toLowerCase().contains("removed"));
        assertTrue(bobby.getResponse("find,eggs").toLowerCase().contains("eggs"));
        assertTrue(bobby.getResponse("clients").contains("Alice"));
        assertTrue(bobby.getResponse("addclient Jane,789,40,Lawyer,PolicyC").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("deleteclient,1").toLowerCase().contains("removed this client"));
        assertTrue(bobby.getResponse("editclient 1,name,Jane").toLowerCase().contains("updated"));
        assertTrue(bobby.getResponse("nonsense").toLowerCase().contains("oops"));
    }
    */
}


