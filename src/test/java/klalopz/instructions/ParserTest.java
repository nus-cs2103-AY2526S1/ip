package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void parse_todoCommand_returnsToDoInstruction() throws Exception {
        Instruction instr = Parser.parse("todo read book");
        assertInstanceOf(ToDoInstruction.class, instr);
    }

    @Test
    void parse_deadlineCommand_returnsDeadlineInstruction() throws Exception {
        Instruction instr = Parser.parse("deadline project / 01-12-2025");
        assertInstanceOf(DeadlineInstruction.class, instr);
    }

    @Test
    void parse_eventCommand_returnsEventInstruction() throws Exception {
        Instruction instr = Parser.parse("event concert / 01-01-2025 / 01-02-2025");
        assertInstanceOf(EventInstruction.class, instr);
    }

    @Test
    void parse_listCommand_returnsListInstruction() throws Exception {
        Instruction instr = Parser.parse("list");
        assertInstanceOf(ListInstruction.class, instr);
    }
}
