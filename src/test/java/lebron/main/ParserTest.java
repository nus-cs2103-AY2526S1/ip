package lebron.main;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.ToDo;

public class ParserTest {
    @Test
    public void testParseTask_validTask_success() {
        String todoLine = "T|1|Read a book";
        Task todoTask = Parser.parseTask(todoLine);
        assert todoTask instanceof ToDo;
        assert todoTask.isDone();
        assert todoTask.getDescription().equals("Read a book");

        String deadlineLine = "D|0|Submit report|2023-10-01";
        Task deadlineTask = Parser.parseTask(deadlineLine);
        assert deadlineTask instanceof Deadline;
        assert !deadlineTask.isDone();
        assert deadlineTask.getDescription().equals("Submit report");
        assert ((Deadline) deadlineTask).getBy().equals(LocalDate.parse("2023-10-01"));

        String eventLine = "E|1|Team meeting|2023-10-05|2023-10-05";
        Task eventTask = Parser.parseTask(eventLine);
        assert eventTask instanceof Event;
        assert eventTask.isDone();
        assert eventTask.getDescription().equals("Team meeting");
        assert ((Event) eventTask).getStart().equals(LocalDate.parse("2023-10-05"));
        assert ((Event) eventTask).getEnd().equals(LocalDate.parse("2023-10-05"));
    }
}
