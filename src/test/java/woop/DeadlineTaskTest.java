package woop;

import org.junit.jupiter.api.Test;
import woop.logic.DeadlineTask;
import woop.logic.IllegalDescriptorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineTaskTest {
    @Test
    public void initialise_badDate_descriptorExceptionThrown() {
        assertThrows(IllegalDescriptorException.class,
                () -> new DeadlineTask("Give Book", "2022-32-10"));
    }

    @Test
    public void initialise_incompleteDate_descriptorExceptionThrown() {
        assertThrows(IllegalDescriptorException.class,
                () -> new DeadlineTask("Give Book", "2022-12"));
    }

    @Test
    public void equals_sameDate_true() throws IllegalDescriptorException {
        assertEquals(new DeadlineTask("Give Book", "2022-12-10"),
                new DeadlineTask("Give Book", "2022-12-10"));
    }

    @Test
    public void equals_differentDate_false() throws IllegalDescriptorException {
        assertNotEquals(new DeadlineTask("Give Book", "2022-11-10"),
                new DeadlineTask("Give Book", "2022-12-10"));
    }

    @Test
    public void equals_differentName_false() throws IllegalDescriptorException {
        assertNotEquals(new DeadlineTask("Give Bok", "2022-12-10"),
                new DeadlineTask("Give Book", "2022-12-10"));
    }

    @Test
    public void equals_differentIsFinished_false() throws  IllegalDescriptorException {
        assertNotEquals(new DeadlineTask("Give Book", "2022-12-10"),
                new DeadlineTask("Give Book", true ,"","2022-12-10"));
    }
}