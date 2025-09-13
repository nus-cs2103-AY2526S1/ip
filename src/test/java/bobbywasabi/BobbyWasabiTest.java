package bobbywasabi;

import bobbywasabi.client.ClientList;
import bobbywasabi.storage.Storage;
import bobbywasabi.tasks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

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

        bobby.processTodoCommand("todo, Read book");
        bobby.processDeadlineCommand("deadline, Submit report , 20/12/3100 2359");
        bobby.processAddClient("addclient , Alice,12345678,30,Engineer,PolicyA");
    }

    @Test
    void testProcessMarkCommand_markValidTask_success() {
        String result = bobby.processMarkCommand("mark,1");
        assertTrue(bobby.processListCommand().contains("[X] Read book"));
        assertTrue(result.toLowerCase().contains("mark"));
    }

    @Test
    void testProcessMarkCommand_invalidIndex_throwsException() {
        String result = bobby.processMarkCommand("mark,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessUnmarkCommand_unmarkValidTask_success() {
        bobby.processMarkCommand("mark,1");
        String result = bobby.processUnmarkCommand("unmark,1");
        assertTrue(bobby.processListCommand().contains("[ ] Read book"));
        assertTrue(result.toLowerCase().contains("not done yet"));
    }

    @Test
    void testProcessTodoCommand_addValidTodo_success() {
        String result = bobby.processTodoCommand("todo , Buy milk");
        assertTrue(bobby.processListCommand().contains("Buy milk"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessTodoCommand_missingDescription_throwsException() {
        String result = bobby.processTodoCommand("todo ");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeadlineCommand_addValidDeadline_success() {
        String result = bobby.processDeadlineCommand("deadline , Finish project,31/12/2099 2359");
        assertTrue(bobby.processListCommand().contains("Finish project"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessDeadlineCommand_invalidFormat_throwsException() {
        String result = bobby.processDeadlineCommand("deadline, Finish project, 31-12-2099 2359");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessEventCommand_addValidEvent_success() {
        String result = bobby.processEventCommand("event,   Meeting, 31/12/2099 1000, 31/12/3000 1100");
        assertTrue(bobby.processListCommand().contains("Meeting"));
        assertTrue(result.toLowerCase().contains("add"));
    }

    @Test
    void testProcessEventCommand_sameStartAndEndTime_throwsException() {
        String result = bobby.processEventCommand("event Meeting, 31/12/2099 1000, 31/12/2099 1000");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeleteCommand_deleteValidTask_success() {
        int before = bobby.processListCommand().split("\n").length;
        String result = bobby.processDeleteCommand("delete,1");
        int after = bobby.processListCommand().split("\n").length;
        assertTrue(result.toLowerCase().contains("removed"));
        assertEquals(before - 1, after);
    }

    @Test
    void testProcessDeleteCommand_indexOutOfRange_throwsException() {
        String result = bobby.processDeleteCommand("delete,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessFindCommand_findExistingTask_success() {
        String result = bobby.processFindCommand("find,book");
        assertTrue(result.toLowerCase().contains("read book"));
    }

    @Test
    void testProcessFindCommand_emptyKeyword_throwsException() {
        String result = bobby.processFindCommand("find,");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessEditClient_editValidClientName_success() {
        String result = bobby.processEditClient("editclient ,1,name,Bob");
        assertTrue(result.toLowerCase().contains("updated"));
        assertTrue(bobby.processClientsCommand().contains("Bob"));
    }

    @Test
    void testProcessEditClient_editValidClientContact_success() {
        String result = bobby.processEditClient("editclient ,1,contactnumber, 89898989");
        assertTrue(result.toLowerCase().contains("updated"));
        assertTrue(bobby.processClientsCommand().contains("89898989"));
    }

    @Test
    void testProcessEditClient_invalidClientIndex_throwsException() {
        String result = bobby.processEditClient("editclient , 100,name,Bob");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessDeleteClient_deleteValidClient_success() {
        int before = bobby.processClientsCommand().split("\n").length;
        String result = bobby.processDeleteClient("deleteclient,1");
        int after = bobby.processClientsCommand().split("\n").length;
        assertTrue(result.toLowerCase().contains("removed this client"));
    }

    @Test
    void testProcessDeleteClient_invalidClientIndex_throwsException() {
        String result = bobby.processDeleteClient("deleteclient,100");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessAddClient_addValidClient_success() {
        String result = bobby.processAddClient("addclient , John,   45664545 ,25,Doctor,PolicyB");
        assertTrue(result.toLowerCase().contains("add"));
        assertTrue(bobby.processClientsCommand().contains("John"));
    }

    @Test
    void testProcessAddClient_invalidContact_throwsException() {
        String result = bobby.processAddClient("addclient, John, 4566 ,25,Doctor,PolicyB");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessAddClient_invalidClientDetails_throwsExeption() {
        String result = bobby.processAddClient("addclient, Joh, 24555555, 23 ");
        assertTrue(result.toLowerCase().contains("oops"));
    }

    @Test
    void testProcessClientsCommand_listClients_success() {
        String result = bobby.processClientsCommand();
        assertTrue(result.contains("Alice"));
    }

    @Test
    void testProcessByeCommand_exit_success() {
        String result = bobby.processByeCommand();
        assertTrue(result.toLowerCase().contains("bye"));
    }

    @Test
    void testProcessListCommand_listTasks_success() {
        String result = bobby.processListCommand();
        assertTrue(result.contains("Read book"));
        assertTrue(result.contains("Submit report"));
    }

    @Test
    void testProcessDefaultCommand_unrecognisedCommand_success() {
        String result = bobby.processDefaultCommand();
        assertTrue(result.toLowerCase().contains("oops"));
    }


    @Test
    void testGetResponse_allCommands_success() {
        assertTrue(bobby.getResponse("bye").toLowerCase().contains("bye"));
        assertTrue(bobby.getResponse("list").contains("Read book"));
        assertTrue(bobby.getResponse("mark,1").toLowerCase().contains("mark"));
        assertTrue(bobby.getResponse("unmark,1").toLowerCase().contains("not done yet"));
        assertTrue(bobby.getResponse("todo, Buy eggs").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("deadline , Do,31/12/2099 2359").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("event ,Party,31/12/2099 1000, 31/12/2099 1001").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("delete,1").toLowerCase().contains("removed"));
        assertTrue(bobby.getResponse("find,eggs").toLowerCase().contains("eggs"));
        assertTrue(bobby.getResponse("clients").contains("Alice"));
        assertTrue(bobby.getResponse("addclient,Jane,78988888,40,Lawyer,PolicyC").toLowerCase().contains("add"));
        assertTrue(bobby.getResponse("deleteclient,1").toLowerCase().contains("removed this client"));
        assertTrue(bobby.getResponse("editclient ,1,name,Jane").toLowerCase().contains("updated"));
        assertTrue(bobby.getResponse("nonsense").toLowerCase().contains("oops"));
    }

}


