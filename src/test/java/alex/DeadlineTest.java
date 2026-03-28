package alex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toFileString_correctFormat() {
        assertEquals("D / 1 / read book / Oct 15 2025",
                     new Deadline("read book", "Oct 15 2025").toFileString());

        Deadline d = new Deadline("sleep", "Jun 20 2020");
        d.markTask();
        assertEquals("D / 0 / sleep / Jun 20 2020", d.toFileString());
    }
}
