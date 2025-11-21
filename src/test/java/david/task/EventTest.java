package david.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() throws Exception {
        Event test1 = new Event("test1", "Mon 2pm", "4pm");
        Event test2 = new Event("test2", "2025-08-17",
                                                            "2025-08-25 1800");
        assertEquals("[E][ ] test1 (from: Mon 2pm to: 4pm)", test1.toString());
        assertEquals("[E][ ] test2 (from: Aug 17 2025 to: Aug 25 2025, 6:00PM)",
                                                                        test2.toString());
    }

    @Test
    public void markTest() throws Exception {
        Event test2 = new Event("test2", "2025-08-17",
                "2025-08-25 1800");
        test2.markAsDone();
        assertEquals("[E][X] test2 (from: Aug 17 2025 to: Aug 25 2025, 6:00PM)",
                                                                        test2.toString());
        test2.markAsUndone();
        assertEquals("[E][ ] test2 (from: Aug 17 2025 to: Aug 25 2025, 6:00PM)",
                                                                        test2.toString());
    }
}
