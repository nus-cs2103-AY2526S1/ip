package bro.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class LauncherTest {
    @Test
    public void main_runsWithoutException() {
        assertDoesNotThrow(() -> Launcher.main(new String[] {}));
    }
}
