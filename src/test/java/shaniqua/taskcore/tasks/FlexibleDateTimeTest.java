package shaniqua.taskcore.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FlexibleDateTimeTest {

    @Test
    void testParse() throws Exception {
        String input = "2019-01-15 1600";
        String actual = "2019-01-15T16:00:00";
        assertEquals(FlexibleDateTime.parse(input).get(), LocalDateTime.parse(actual));
    }

    @Test
    void testToString() throws Exception {
        FlexibleDateTime test = FlexibleDateTime.parse("2019-02-01 1600");
        String actual = "Feb 1 2019 4 pm";
        assert(actual.equalsIgnoreCase(test.toString()));
    }
}