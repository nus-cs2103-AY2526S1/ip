package utils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import exceptions.ApunableException;

public class DateTimeUtilTest {
    @Test
    public void normalTest() throws Exception {
        assertEquals(LocalDateTime.of(2025, 9, 11, 15, 49), 
                DateTimeUtil.tryParse("11/9/2025 1549"));
    }

    @Test
    public void stricterTest() throws Exception {
        assertEquals(LocalDateTime.of(2025, 9, 11, 15, 49), 
                DateTimeUtil.tryParse("11-9-25T15:49"));
    }

    @Test
    public void exceptionTest() throws Exception {
        ApunableException thrown = assertThrows(
            ApunableException.class,
            () -> DateTimeUtil.tryParse("11/9 1549"),
            "Expected tryParse to throw IllegalArgumentException, but it didn't"
        );
    }
}
