package inputhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import command.Command;
import command.Commands;
import exceptions.MarkExceptions;
import task.TaskList;


public class InputHandlerTest {

    @Test
    public void handle_todoInput_todoCommandCreated() throws MarkExceptions {
        String input = "todo buy books";
        Command command = InputHandler.handle(input, new TaskList());


        assertEquals(Commands.TODO.create("buy books", new TaskList()).getClass(), command.getClass());
        assertEquals(Commands.TODO.create("buy books", new TaskList()).toString(), command.toString());
    }
}
