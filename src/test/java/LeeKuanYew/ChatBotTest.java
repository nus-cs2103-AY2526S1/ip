package LeeKuanYew;

import LeeKuanYew.Command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatBotTest {

    @Test
    public void parseCommand_bye_returnsByeCommand() throws Exception {
        ChatBot bot = new ChatBot();
        Command cmd = bot.parseCommand("bye");
        assertTrue(cmd instanceof ByeCommand);
    }

    @Test
    public void parseCommand_todo_returnsToDoCommand() throws Exception {
        ChatBot bot = new ChatBot();
        Command cmd = bot.parseCommand("todo read book");
        assertTrue(cmd instanceof ToDoCommand);
    }

    @Test
    public void parseCommand_invalidCommand_throwsException() {
        ChatBot bot = new ChatBot();
        Exception ex = assertThrows(Exception.class, () -> bot.parseCommand("unknown"));
        assertEquals("YOUR WORDS MEAN NOTHING!", ex.getMessage());
    }

    @Test
    public void parseCommand_markWithoutNumber_throwsException() {
        ChatBot bot = new ChatBot();
        Exception ex = assertThrows(Exception.class, () -> bot.parseCommand("mark"));
        assertEquals("You must specify a task number!", ex.getMessage());
    }
}