package hermione.utils;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hermione.tasks.Deadline;
import hermione.tasks.Event;
import hermione.tasks.FixedDurationTask;
import hermione.tasks.Task;
import hermione.tasks.ToDo;

public class StorageUtilsTest {

    private StorageUtils storageUtils;

    @BeforeEach
    public void setUp() {
        storageUtils = new StorageUtils();
    }

    // Serialization Tests
    @Test
    public void serialize_toDoTask_returnsCorrectString() {
        ToDo todo = new ToDo("Read book", false);
        String result = storageUtils.serialize(todo);
        String expected = "T, 0, Read book";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_completedToDoTask_returnsCorrectString() {
        ToDo todo = new ToDo("Read book", true);
        String result = storageUtils.serialize(todo);
        String expected = "T, 1, Read book";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_deadlineTask_returnsCorrectString() {
        LocalDateTime deadline = LocalDateTime.of(2025, 3, 19, 16, 0);
        Deadline task = new Deadline("Return book", false, deadline);
        String result = storageUtils.serialize(task);
        String expected = "D, 0, Return book, Mar 19 2025 16:00";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_eventTask_returnsCorrectString() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 19, 16, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 19, 18, 0);
        Event task = new Event("Project meeting", false, from, to);
        String result = storageUtils.serialize(task);
        String expected = "E, 0, Project meeting, Mar 19 2025 16:00, Mar 19 2025 18:00";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void serialize_fixedDurationTask_returnsCorrectString() {
        FixedDurationTask task = new FixedDurationTask("Study for exam", false, 120);
        String result = storageUtils.serialize(task);
        String expected = "F, 0, Study for exam, 120";
        Assertions.assertEquals(expected, result);
    }

    // Deserialization Tests
    @Test
    public void deserialize_toDoTask_returnsCorrectTask() {
        String line = "T, 0, Read book";
        Task result = storageUtils.deserialize(line);

        Assertions.assertTrue(result instanceof ToDo);
        ToDo todo = (ToDo) result;
        Assertions.assertEquals("Read book", todo.getDescription());
        Assertions.assertFalse(todo.isCompleted());
    }

    @Test
    public void deserialize_completedToDoTask_returnsCorrectTask() {
        String line = "T, 1, Read book";
        Task result = storageUtils.deserialize(line);

        Assertions.assertTrue(result instanceof ToDo);
        ToDo todo = (ToDo) result;
        Assertions.assertEquals("Read book", todo.getDescription());
        Assertions.assertTrue(todo.isCompleted());
    }

    @Test
    public void deserialize_deadlineTask_returnsCorrectTask() {
        String line = "D, 0, Return book, Mar 19 2025 16:00";
        Task result = storageUtils.deserialize(line);

        Assertions.assertTrue(result instanceof Deadline);
        Deadline deadline = (Deadline) result;
        Assertions.assertEquals("Return book", deadline.getDescription());
        Assertions.assertFalse(deadline.isCompleted());
        Assertions.assertEquals(LocalDateTime.of(2025, 3, 19, 16, 0), deadline.getBy());
    }

    @Test
    public void deserialize_eventTask_returnsCorrectTask() {
        String line = "E, 0, Project meeting, Mar 19 2025 16:00, Mar 19 2025 18:00";
        Task result = storageUtils.deserialize(line);

        Assertions.assertTrue(result instanceof Event);
        Event event = (Event) result;
        Assertions.assertEquals("Project meeting", event.getDescription());
        Assertions.assertFalse(event.isCompleted());
        Assertions.assertEquals(LocalDateTime.of(2025, 3, 19, 16, 0), event.getFrom());
        Assertions.assertEquals(LocalDateTime.of(2025, 3, 19, 18, 0), event.getTo());
    }

    @Test
    public void deserialize_fixedDurationTask_returnsCorrectTask() {
        String line = "F, 0, Study for exam, 120";
        Task result = storageUtils.deserialize(line);

        Assertions.assertTrue(result instanceof FixedDurationTask);
        FixedDurationTask task = (FixedDurationTask) result;
        Assertions.assertEquals("Study for exam", task.getDescription());
        Assertions.assertFalse(task.isCompleted());
        Assertions.assertEquals(120, task.getDuration());
    }

    // Error Handling Tests
    @Test
    public void deserialize_insufficientFields_throwsException() {
        String line = "T, 0";
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> storageUtils.deserialize(line));
    }

    @Test
    public void deserialize_invalidTaskType_throwsException() {
        String line = "X, 0, Invalid task";
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> storageUtils.deserialize(line));
    }

    @Test
    public void deserialize_invalidBinaryValue_throwsException() {
        String line = "T, 2, Read book";
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> storageUtils.deserialize(line));
    }

    @Test
    public void deserialize_emptyLine_throwsException() {
        String line = "";
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> storageUtils.deserialize(line));
    }

    // Round-trip Tests (serialize then deserialize)
    @Test
    public void serializeDeserialize_toDoTask_returnsSameTask() {
        ToDo original = new ToDo("Read book", false);
        String serialized = storageUtils.serialize(original);
        Task deserialized = storageUtils.deserialize(serialized);

        Assertions.assertTrue(deserialized instanceof ToDo);
        ToDo result = (ToDo) deserialized;
        Assertions.assertEquals(original.getDescription(), result.getDescription());
        Assertions.assertEquals(original.isCompleted(), result.isCompleted());
    }

    @Test
    public void serializeDeserialize_deadlineTask_returnsSameTask() {
        LocalDateTime deadline = LocalDateTime.of(2025, 3, 19, 16, 0);
        Deadline original = new Deadline("Return book", true, deadline);
        String serialized = storageUtils.serialize(original);
        Task deserialized = storageUtils.deserialize(serialized);

        Assertions.assertTrue(deserialized instanceof Deadline);
        Deadline result = (Deadline) deserialized;
        Assertions.assertEquals(original.getDescription(), result.getDescription());
        Assertions.assertEquals(original.isCompleted(), result.isCompleted());
        Assertions.assertEquals(original.getBy(), result.getBy());
    }

    @Test
    public void serializeDeserialize_eventTask_returnsSameTask() {
        LocalDateTime from = LocalDateTime.of(2025, 3, 19, 16, 0);
        LocalDateTime to = LocalDateTime.of(2025, 3, 19, 18, 0);
        Event original = new Event("Project meeting", true, from, to);
        String serialized = storageUtils.serialize(original);
        Task deserialized = storageUtils.deserialize(serialized);

        Assertions.assertTrue(deserialized instanceof Event);
        Event result = (Event) deserialized;
        Assertions.assertEquals(original.getDescription(), result.getDescription());
        Assertions.assertEquals(original.isCompleted(), result.isCompleted());
        Assertions.assertEquals(original.getFrom(), result.getFrom());
        Assertions.assertEquals(original.getTo(), result.getTo());
    }

    @Test
    public void serializeDeserialize_fixedDurationTask_returnsSameTask() {
        FixedDurationTask original = new FixedDurationTask("Study for exam", true, 120);
        String serialized = storageUtils.serialize(original);
        Task deserialized = storageUtils.deserialize(serialized);

        Assertions.assertTrue(deserialized instanceof FixedDurationTask);
        FixedDurationTask result = (FixedDurationTask) deserialized;
        Assertions.assertEquals(original.getDescription(), result.getDescription());
        Assertions.assertEquals(original.isCompleted(), result.isCompleted());
        Assertions.assertEquals(original.getDuration(), result.getDuration());
    }
}
