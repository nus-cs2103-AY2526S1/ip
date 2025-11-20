package jobe.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jobe.exception.JobeException;

// This test file was generated using ChatGPT as part of the
// A-AiAssisted increment.
public class EventTaskTest {
    
    /**
     * Test that the EventTask toString() returns the correct prefix for a completed jobe.task.
     * <p>
     * Specifically, we only test that it starts with "[X]".
     * The rest of the string is verified in the superclass tests.
     * </p>
     */
    @Test
    public void testToString_includesCorrectPrefix() throws JobeException {
        EventTask event = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        String result = event.toString();
        assertTrue(result.startsWith("[E]"));
    }
    
    @Test
    public void testToString_includesCorrectSuffix() throws JobeException {
        EventTask event = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        String result = event.toString();
        assertTrue(result.endsWith("(Aug 24 2024, 10:00 -> Aug 24 2024, 12:00)"));
    }
    
    @Test
    public void testFileConversion_includesCorrectPrefix() throws JobeException {
        EventTask event = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        String result = event.convertToFileFormat();
        assertTrue(result.startsWith("E / "));
    }
    
    @Test
    public void testFileConversion_includesCorrectSuffix() throws JobeException {
        EventTask event = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        String result = event.convertToFileFormat();
        assertTrue(result.endsWith("Aug 24 2024, 10:00 / Aug 24 2024, 12:00"));
    }
    
    @Test
    public void testCreateEventTask_endBeforeStart_throwsException() {
        JobeException exception = assertThrows(JobeException.class, () -> {
            EventTask.createEventTask("test",
                    "from 24/08/2024 1400",
                    "to 24/08/2024 1200"
            );
        });
        assertTrue(exception.getMessage().contains("OOPS!!!! Your end date is before your start date. "
                + "Please check your input dates!"
        ));
    }
    
    @Test
    public void testIsDuplicate_overlap() throws JobeException {
        EventTask event1 = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        EventTask event2 = EventTask.createEventTask("test",
                "from 24/08/2024 1000",
                "to 24/08/2024 1200"
        );
        
        assertTrue(event1.isDuplicate(event2));
    }
}

